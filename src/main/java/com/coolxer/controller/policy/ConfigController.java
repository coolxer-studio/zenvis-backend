package com.coolxer.controller.policy;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.configuration.CustomWebConfig;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.policy.dto.ConfigDto;
import com.coolxer.model.policy.vo.ConfigVo;
import com.coolxer.service.config.ConfigService;
import com.coolxer.utils.JacksonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 策略配置
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api/v1/config/{type}")
public class ConfigController {

    @Autowired
    private CustomWebConfig customWebConfig;

    @Autowired
    private ConfigService configService;

    /**
     * 文件树
     *
     * @return 结果
     */
    @GetMapping(value = "/tree")
    @ApiOperation(value = "文件树", notes = "配置文件树")
    public ResponseWrap<List<ConfigVo>> getConfigFileTree(@PathVariable("type") String type) {
        return ResponseWrap.success(configService.getConfigFileTree(type));
    }

    /**
     * 读文件
     *
     * @param fileName 文件全名，带路径
     * @return 文件内容
     */
    @GetMapping(value = "/schema")
    @ApiOperation(value = "获取文件schema", notes = "获取文件scheme")
    public ResponseWrap<Object> scheme(@PathVariable("type") String type, @RequestParam(value = "file_name") String fileName) {
        String schema = configService.readFileSchema(type, fileName);
        Object jsonObject = JacksonUtil.toObject(schema, Object.class);
        return ResponseWrap.success(jsonObject);
    }

    /**
     * 读文件
     *
     * @param fileName 文件全名，带路径
     * @return 文件内容
     */
    @GetMapping(value = "/read")
    @ApiOperation(value = "读文件", notes = "读文件")
    public ResponseWrap<String> readFile(@PathVariable("type") String type, @RequestParam(value = "file_name") String fileName) {
        if (configService.fileExistsInConfigPath(type, fileName)) {
            return ResponseWrap.success(configService.readFile(type, fileName));
        }
        return ResponseWrap.fail(ResultCodeEnum.NO_AUTHORITY);
    }

    /**
     * 获取配置
     *
     * @param fileName 配置文件
     * @return 配置文件内容，不包含包装数据
     */
    @GetMapping(value = "/get")
    @ApiOperation(value = "获取配置", notes = "获取配置")
    public Object getConfig(@PathVariable("type") String type, @RequestParam(value = "file_name") String fileName) {
        if (configService.fileExistsInConfigPath(type, fileName)) {
            return configService.readFile(type, fileName);
        }
        return ResponseWrap.fail(ResultCodeEnum.NO_AUTHORITY);
    }

    /**
     * 修改文件
     *
     * @param configDto 配置文件实体
     * @return 结果
     */
    @PostMapping(value = "/modify")
    @ApiOperation(value = "修改文件", notes = "修改文件")
    public ResponseWrap<Void> modify(@PathVariable("type") String type, @RequestBody ConfigDto configDto) {
        if (configService.fileExistsInConfigPath(type, configDto.getFileName())) {
            configService.modifyConfig(type, configDto);
            return ResponseWrap.success();
        }
        return ResponseWrap.fail(ResultCodeEnum.NO_AUTHORITY);
    }

    /**
     * 修改文件
     *
     * @param configDto 配置文件实体
     * @return 结果
     */
    @PostMapping(value = "/apply")
    @ApiOperation(value = "应用配置", notes = "应用配置")
    public ResponseWrap<Void> apply(@PathVariable("type") String type, @RequestBody ConfigDto configDto) {
        if (configService.fileExistsInConfigPath(type, configDto.getFileName())) {
            // 先保存
            configService.modifyConfig(type, configDto);
            // 后执行策略
            configService.applyPolicy(type, configDto);
            return ResponseWrap.success();
        }
        return ResponseWrap.fail(ResultCodeEnum.NO_AUTHORITY);
    }

    /**
     * 添加文件
     *
     * @param type
     * @param configDto 配置文件实体
     * @return
     */
    @PostMapping(value = "/add")
    @ApiOperation(value = "添加文件", notes = "添加文件")
    public ResponseWrap<String> addFile(@PathVariable("type") String type, @RequestBody ConfigDto configDto) {
        if (configService.fileExistsInConfigPath(type, configDto.getFileName())) {
            return ResponseWrap.fail(ResultCodeEnum.NO_AUTHORITY);
        }
        if (configService.addFile(type, configDto.getFileName())) {
            return ResponseWrap.success();
        } else {
            return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
        }
    }

    /**
     * 修改文件名
     *
     * @param type
     * @param configDto 配置文件实体
     * @return
     */
    @PostMapping(value = "/rename")
    @ApiOperation(value = "修改文件名", notes = "修改文件名")
    public ResponseWrap<String> renameFile(@PathVariable("type") String type, @RequestBody ConfigDto configDto) {
        if (configService.fileExistsInConfigPath(type, configDto.getOriginalFileName())) {
            if (configService.renameFile(type, configDto.getOriginalFileName(), configDto.getFileName())) {
                return ResponseWrap.success();
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        }
        return ResponseWrap.fail(ResultCodeEnum.NO_AUTHORITY);
    }

    /**
     * 删除文件
     *
     * @param type
     * @param configDto 配置文件实体
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiOperation(value = "文件名", notes = "文件名")
    public ResponseWrap<String> deleteFile(@PathVariable("type") String type, @RequestBody ConfigDto configDto) {
        if (configService.fileExistsInConfigPath(type, configDto.getFileName())) {
            if (configService.deleteFile(type, configDto.getFileName())) {
                return ResponseWrap.success();
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        }
        return ResponseWrap.fail(ResultCodeEnum.NO_AUTHORITY);
    }

}
