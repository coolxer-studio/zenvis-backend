package com.coolxer.configuration.extend;

import io.github.classgraph.ClassGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPatternParser;

import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class Registrar {

    @Autowired
    private ConfigurableApplicationContext ctx;

    @Autowired
    @Qualifier("extendJarHandlerMapping")
    private RequestMappingHandlerMapping extendJarHandlerMapping;

    private final Map<String, Object> pluginControllers = new ConcurrentHashMap<>();

    public void register(ExtendJar desc, URLClassLoader cl) throws Exception {
        // 1) 扫描类
        List<Class<?>> classes = new ClassGraph()
                .enableAllInfo()
                .overrideClassLoaders(cl)
                .acceptPackages(desc.getScanPackage())
                .scan()
                .getAllClasses()
                .filter(classInfo ->
                        classInfo.hasAnnotation(RestController.class) ||
                                classInfo.hasAnnotation(Service.class)
                )
                .loadClasses(true);

        // 对classes进行排序
        classes.sort((c1, c2) -> {
            int p1 = priority(c1);
            int p2 = priority(c2);
            return Integer.compare(p1, p2);   // 越小越靠前
        });

        for (Class<?> clazz : classes) {
            String beanName = desc.beanNameBuild(clazz.getName());
            // 2) 创建并注入 Bean
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(clazz);
            beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
            ((BeanDefinitionRegistry) ctx.getBeanFactory()).registerBeanDefinition(beanName, beanDefinition);
            Object bean = ctx.getBean(beanName);   // 或者 ctx.getBean(clazz);
            // 3) 注册 Controller Mapping
            if (clazz.isAnnotationPresent(RestController.class)) {
                pluginControllers.put(beanName, bean);
                registerHandlerMethods(bean, desc);
            }
        }
    }

    private static int priority(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Service.class)) return 0;
        if (clazz.isAnnotationPresent(RestController.class)) return 1;
        return 2; // 其他类
    }

    private void registerHandlerMethods(Object controller, ExtendJar extendJar) {
        Map<Method, RequestMappingInfo> infos = new ExtendJarRequestMappingBuilder(extendJar)
                .build(controller.getClass());
        infos.forEach((method, info) -> extendJarHandlerMapping.registerMapping(info, controller, method));
    }

    public class ExtendJarRequestMappingBuilder {

        private final ExtendJar extendJar;

        public ExtendJarRequestMappingBuilder(ExtendJar extendJar) {
            this.extendJar = extendJar;
        }

        public Map<Method, RequestMappingInfo> build(Class<?> controllerClass) {
            // 使用 AnnotatedElementUtils 获取 @RequestMapping 注解
            RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(controllerClass, RequestMapping.class);
            String prefixPath = "";
            if (requestMapping != null) {
                // 类上加了url路径，获取路径
                prefixPath = first(requestMapping.path(), requestMapping.value());
            }
            Map<Method, RequestMappingInfo> map = new HashMap<>();
            for (Method m : controllerClass.getDeclaredMethods()) {
                RequestMappingInfo info = create(prefixPath, m);
                if (info != null) map.put(m, info);
            }
            return map;
        }

        private RequestMappingInfo create(String prefixPath, Method m) {
            RequestMapping ann = AnnotatedElementUtils.findMergedAnnotation(m, RequestMapping.class);
            if (ann != null) {
                // 创建 PathPatternParser
                PathPatternParser parser = new PathPatternParser();
                // 解析路径模式
                String fullPath = extendJar.fullPathBuild(prefixPath + first(ann.path(), ann.value()));
                // 使用 RequestMappingInfo.Builder 构建
                RequestMappingInfo.Builder builder = RequestMappingInfo
                        .paths(fullPath)
                        .methods(ann.method())
                        .params(ann.params())
                        .headers(ann.headers())
                        .consumes(ann.consumes())
                        .produces(ann.produces());

                // 设置配置
                RequestMappingInfo.BuilderConfiguration options = new RequestMappingInfo.BuilderConfiguration();
                options.setPatternParser(parser);
                builder.options(options);
                return builder.build();
            }
            // GetMapping / PostMapping ...
            GetMapping get = AnnotatedElementUtils.findMergedAnnotation(m, GetMapping.class);
            if (get != null) {
                // 创建 PathPatternParser
                PathPatternParser parser = new PathPatternParser();
                // 解析路径模式
                String fullPath = extendJar.fullPathBuild(prefixPath + first(get.path(), get.value()));
                // 使用 RequestMappingInfo.Builder 构建
                RequestMappingInfo.Builder builder = RequestMappingInfo
                        .paths(fullPath)
                        .methods(RequestMethod.GET)
                        .params(get.params())
                        .headers(get.headers())
                        .consumes(get.consumes())
                        .produces(get.produces());

                // 设置配置
                RequestMappingInfo.BuilderConfiguration options = new RequestMappingInfo.BuilderConfiguration();
                options.setPatternParser(parser);
                builder.options(options);
                return builder.build();
            }
            // 同理 PostMapping ...
            PostMapping post = AnnotatedElementUtils.findMergedAnnotation(m, PostMapping.class);
            if (post != null) {
                // 创建 PathPatternParser
                PathPatternParser parser = new PathPatternParser();
                // 解析路径模式
                String fullPath = extendJar.fullPathBuild(prefixPath + first(post.path(), post.value()));
                // 使用 RequestMappingInfo.Builder 构建
                RequestMappingInfo.Builder builder = RequestMappingInfo
                        .paths(fullPath)
                        .methods(RequestMethod.POST)
                        .params(post.params())
                        .headers(post.headers())
                        .consumes(post.consumes())
                        .produces(post.produces());

                // 设置配置
                RequestMappingInfo.BuilderConfiguration options = new RequestMappingInfo.BuilderConfiguration();
                options.setPatternParser(parser);
                builder.options(options);
                return builder.build();
            }
            return null;
        }

        private String first(String[] a, String[] b) {
            return a.length > 0 ? a[0] : (b.length > 0 ? b[0] : "");
        }
    }
}
