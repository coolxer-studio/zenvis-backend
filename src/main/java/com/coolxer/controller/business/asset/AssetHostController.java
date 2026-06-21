package com.coolxer.controller.business.asset;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.business.asset.dto.AssetHostDto;
import com.coolxer.model.business.asset.dto.AssetHostSearchDto;
import com.coolxer.model.business.asset.vo.AssetHostDetailVo;
import com.coolxer.model.business.asset.vo.AssetHostVo;
import com.coolxer.service.business.asset.AssetHostService;
import com.coolxer.utils.CommonUtil;
import com.coolxer.utils.JacksonUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产管理-主机
 */
@Tag(name = "资产管理-主机")
@Slf4j
@RestController
@RequestMapping("/api/v1/asset/host")
public class AssetHostController extends BaseController {

    @Autowired
    private AssetHostService assetHostService;


    @PostMapping({"/add"})
    public ResponseWrap<?> add(@RequestBody AssetHostDto assetHostDto) {
        try {
            if (assetHostService.add(assetHostDto)) {
                return ResponseWrap.success("添加成功");
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseWrap<?> delete(@PathVariable("id") String id) {
        try {
            assetHostService.delete(id);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping({"/bulk/{ids}"})
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<String> ids) {
        try {
            assetHostService.deleteALL(ids);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{id}/update"})
    public ResponseWrap<?> update(@PathVariable("id") String id, @RequestBody AssetHostDto assetHostDto) {
        try {
            if (assetHostService.update(id, assetHostDto)) {
                return ResponseWrap.success("修改成功");
            } else
                return ResponseWrap.fail();
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{ids}/bulk_update"})
    public ResponseWrap<?> bulkUpdate(@PathVariable("ids") String[] ids, @RequestBody AssetHostDto assetHostDto) {
        try {
            for (String id : ids) {
                assetHostService.update(id, assetHostDto);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
        return ResponseWrap.success("修改成功");
    }

    @GetMapping({"/list"})
    public ResponseWrap<?> list(AssetHostSearchDto assetHostSearchDto) {
        try {
            PageRowsVo<AssetHostVo> pageDataVo = assetHostService.getPageList(assetHostSearchDto);
            return ResponseWrap.success(pageDataVo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }

    }

    @GetMapping({"/{id}/view"})
    public ResponseWrap<AssetHostVo> query(@PathVariable("id") String id) {
        try {
            AssetHostVo assetHostVo = assetHostService.getOne(id);
            if (assetHostVo == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(assetHostVo);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }

    }


    /**
     * 生成最近7天的日期字符串
     *
     * @return 格式化后的日期字符串列表
     */
    private String generateLastSevenDaysString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();

        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            sb.append("    \"").append(date.format(formatter)).append("\"");
            if (i > 0) {
                sb.append(",\n");
            } else {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    @GetMapping({"/{id}/detail"})
    public ResponseWrap<AssetHostDetailVo> detail(@PathVariable("id") String id) {
        // 定义数据
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"2000-06-05", 116});
        data.add(new Object[]{"2000-06-06", 129});
        data.add(new Object[]{"2000-06-07", 135});
        data.add(new Object[]{"2000-06-08", 86});
        data.add(new Object[]{"2000-06-09", 73});
        data.add(new Object[]{"2000-06-10", 85});
        data.add(new Object[]{"2000-06-11", 73});
        data.add(new Object[]{"2000-06-12", 68});
        data.add(new Object[]{"2000-06-13", 92});
        data.add(new Object[]{"2000-06-14", 130});
        data.add(new Object[]{"2000-0-615", 245});
        data.add(new Object[]{"2000-06-16", 139});
        data.add(new Object[]{"2000-06-17", 115});
        data.add(new Object[]{"2000-06-18", 111});
        data.add(new Object[]{"2000-06-19", 309});
        data.add(new Object[]{"2000-06-20", 206});
        data.add(new Object[]{"2000-06-21", 137});
        data.add(new Object[]{"2000-06-22", 128});
        data.add(new Object[]{"2000-06-23", 85});
        data.add(new Object[]{"2000-06-24", 94});
        data.add(new Object[]{"2000-06-25", 71});
        data.add(new Object[]{"2000-06-26", 106});
        data.add(new Object[]{"2000-06-27", 84});
        data.add(new Object[]{"2000-06-28", 93});
        data.add(new Object[]{"2000-06-29", 85});
        data.add(new Object[]{"2000-06-30", 73});
        data.add(new Object[]{"2000-07-01", 83});
        data.add(new Object[]{"2000-07-02", 125});
        data.add(new Object[]{"2000-07-03", 107});
        data.add(new Object[]{"2000-07-04", 82});
        data.add(new Object[]{"2000-07-05", 44});
        data.add(new Object[]{"2000-07-06", 72});
        data.add(new Object[]{"2000-07-07", 106});
        data.add(new Object[]{"2000-07-08", 107});
        data.add(new Object[]{"2000-07-09", 66});
        data.add(new Object[]{"2000-07-10", 91});
        data.add(new Object[]{"2000-07-11", 92});
        data.add(new Object[]{"2000-07-12", 113});
        data.add(new Object[]{"2000-07-13", 107});
        data.add(new Object[]{"2000-07-14", 131});
        data.add(new Object[]{"2000-07-15", 111});
        data.add(new Object[]{"2000-07-16", 64});
        data.add(new Object[]{"2000-07-17", 69});
        data.add(new Object[]{"2000-07-18", 88});
        data.add(new Object[]{"2000-07-19", 77});
        data.add(new Object[]{"2000-07-20", 83});
        data.add(new Object[]{"2000-07-21", 111});
        data.add(new Object[]{"2000-07-22", 57});
        data.add(new Object[]{"2000-07-23", 55});
        data.add(new Object[]{"2000-07-24", 60});

        // 提取日期列表
        List<String> dateList = new ArrayList<>();
        for (Object[] item : data) {
            dateList.add((String) item[0]);
        }

        // 提取值列表
        List<Integer> valueList = new ArrayList<>();
        for (Object[] item : data) {
            valueList.add((Integer) item[1]);
        }

        List<AssetHostDetailVo.fileDisk> fileDiskList = new ArrayList<>();

        // 假设这些字段是逗号分隔的字符串
        String[] diskNames = new String[]{"/dev", "/data1", "/data2"};
        long[] diskSizes = new long[]{500, 1024, 10240};
        long[] diskUsedSizes = new long[]{200, 240, 124};

        for (int i = 0; i < diskNames.length; i++) {
            AssetHostDetailVo.fileDisk fileDisk = new AssetHostDetailVo.fileDisk();
            fileDisk.setName(diskNames[i]);
            long totalSize = diskSizes[i];
            long usedSize = diskUsedSizes[i];
            fileDisk.setTotal(totalSize);
            fileDisk.setUsed(usedSize);
            // 假设 ratio 是已用空间占总空间的百分比
            if (totalSize > 0) {
                fileDisk.setRatio((int) ((double) usedSize / totalSize * 100));
            } else {
                fileDisk.setRatio(0);  // 防止除以 0
            }
            fileDiskList.add(fileDisk);
        }

        try {
            AssetHostVo assetHostVo = assetHostService.getOne(id);
            AssetHostDetailVo assetHostDetailVo = new AssetHostDetailVo();
            assetHostDetailVo.setAssetHost(assetHostVo);
            assetHostDetailVo.setBootTime("2025-03-04 12:22:10");
            assetHostDetailVo.setRunningStatus("normal");
            assetHostDetailVo.setHealthTrend(new AssetHostDetailVo.HealthTrend(dateList, valueList));
            assetHostDetailVo.setMemoryRatio(67.12);
            assetHostDetailVo.setMemoryUsed(1023L);
            assetHostDetailVo.setCpuRatio(12.2);
            assetHostDetailVo.setCpuUsed(123L);
            assetHostDetailVo.setDiskRatio(30.1);
            assetHostDetailVo.setDiskUsed(312L);
            assetHostDetailVo.setFileDiskList(fileDiskList);
            if (assetHostVo == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(assetHostDetailVo);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }

    }

    @GetMapping({"/{id}/detail_more"})
    public ResponseWrap<Map<String, String>> detailMore(@PathVariable("id") String id) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("key", "value");
            if (map == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(map);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/{id}/service/all"})
    public ResponseWrap<?> getAllService(@PathVariable("id") String id) {
        try {
            String result = "{\n" +
                    "  \"count\": 20," +
                    "  \"rows\": [\n" +
                    "    {\n" +
                    "      \"service_name\": \"Apache Tomcat\",\n" +
                    "      \"service_version\": \"9.0.74\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"2210\",\n" +
                    "      \"process_name\": \"tomcat9\",\n" +
                    "      \"port\": \"8080\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Nginx\",\n" +
                    "      \"service_version\": \"1.22.1\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"1560\",\n" +
                    "      \"process_name\": \"nginx\",\n" +
                    "      \"port\": \"80\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"MySQL Server\",\n" +
                    "      \"service_version\": \"8.0.33\",\n" +
                    "      \"service_status\": \"已停止\",\n" +
                    "      \"process_id\": \"3306\",\n" +
                    "      \"process_name\": \"mysqld\",\n" +
                    "      \"port\": \"3306\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"PostgreSQL\",\n" +
                    "      \"service_version\": \"15.3\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"5432\",\n" +
                    "      \"process_name\": \"postgres\",\n" +
                    "      \"port\": \"5432\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Redis Server\",\n" +
                    "      \"service_version\": \"7.2.4\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"6379\",\n" +
                    "      \"process_name\": \"redis-server\",\n" +
                    "      \"port\": \"6379\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"MongoDB\",\n" +
                    "      \"service_version\": \"6.0.8\",\n" +
                    "      \"service_status\": \"已停止\",\n" +
                    "      \"process_id\": \"27017\",\n" +
                    "      \"process_name\": \"mongod\",\n" +
                    "      \"port\": \"27017\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Memcached\",\n" +
                    "      \"service_version\": \"2.2.0\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"11211\",\n" +
                    "      \"process_name\": \"memcached\",\n" +
                    "      \"port\": \"11211\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Docker Daemon\",\n" +
                    "      \"service_version\": \"24.0.5\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"8082\",\n" +
                    "      \"process_name\": \"dockerd\",\n" +
                    "      \"port\": \"2375\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Node.js\",\n" +
                    "      \"service_version\": \"18.17.0\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"3000\",\n" +
                    "      \"process_name\": \"node\",\n" +
                    "      \"port\": \"3000\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Python HTTP Server\",\n" +
                    "      \"service_version\": \"3.9.18\",\n" +
                    "      \"service_status\": \"已停止\",\n" +
                    "      \"process_id\": \"8000\",\n" +
                    "      \"process_name\": \"python\",\n" +
                    "      \"port\": \"8000\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"RabbitMQ Server\",\n" +
                    "      \"service_version\": \"3.11.14\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"5672\",\n" +
                    "      \"process_name\": \"beam.smp\",\n" +
                    "      \"port\": \"5672\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Elasticsearch\",\n" +
                    "      \"service_version\": \"8.11.2\",\n" +
                    "      \"service_status\": \"已停止\",\n" +
                    "      \"process_id\": \"9200\",\n" +
                    "      \"process_name\": \"java\",\n" +
                    "      \"port\": \"9200\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Kibana\",\n" +
                    "      \"service_version\": \"8.11.2\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"5601\",\n" +
                    "      \"process_name\": \"node\",\n" +
                    "      \"port\": \"5601\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Logstash\",\n" +
                    "      \"service_version\": \"8.11.2\",\n" +
                    "      \"service_status\": \"已停止\",\n" +
                    "      \"process_id\": \"9600\",\n" +
                    "      \"process_name\": \"java\",\n" +
                    "      \"port\": \"9600\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Apache Kafka\",\n" +
                    "      \"service_version\": \"3.6.0\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"9092\",\n" +
                    "      \"process_name\": \"java\",\n" +
                    "      \"port\": \"9092\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"ZooKeeper\",\n" +
                    "      \"service_version\": \"3.8.2\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"2181\",\n" +
                    "      \"process_name\": \"java\",\n" +
                    "      \"port\": \"2181\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Solr Server\",\n" +
                    "      \"service_version\": \"9.1.0\",\n" +
                    "      \"service_status\": \"已停止\",\n" +
                    "      \"process_id\": \"8983\",\n" +
                    "      \"process_name\": \"java\",\n" +
                    "      \"port\": \"8983\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Tomcat\",\n" +
                    "      \"service_version\": \"10.1.12\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"8080\",\n" +
                    "      \"process_name\": \"tomcat\",\n" +
                    "      \"port\": \"8080\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Flask Server\",\n" +
                    "      \"service_version\": \"2.3.3\",\n" +
                    "      \"service_status\": \"运行中\",\n" +
                    "      \"process_id\": \"5000\",\n" +
                    "      \"process_name\": \"flask\",\n" +
                    "      \"port\": \"5000\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"service_name\": \"Django Server\",\n" +
                    "      \"service_version\": \"4.2.6\",\n" +
                    "      \"service_status\": \"已停止\",\n" +
                    "      \"process_id\": \"8000\",\n" +
                    "      \"process_name\": \"runserver\",\n" +
                    "      \"port\": \"8000\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
            Object jsonObject = JacksonUtil.toObject(result, Object.class);
            return ResponseWrap.success(jsonObject);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/label/list"})
    public ResponseWrap<?> listLabel() {
        try {
            List<String> labels = assetHostService.getDistinctLabels();
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(labels,
                    label -> label,  // label 使用原值
                    label -> label   // value 使用原值
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }


    @GetMapping({"/auto_complete/id"})
    public ResponseWrap<?> autoCompleteId(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarIds = assetHostService.getSimilarIds(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarIds,
                    id -> id,    // label 使用原值
                    id -> id     // value 使用原值
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 制造商名称自动补全
     */
    @GetMapping("/auto_complete/manufacturer")
    public ResponseWrap<?> autoCompleteManufacturer(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarManufacturers = assetHostService.getSimilarManufacturers(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarManufacturers,
                    manufacturer -> manufacturer,
                    manufacturer -> manufacturer
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 型号自动补全
     */
    @GetMapping("/auto_complete/model")
    public ResponseWrap<?> autoCompleteModel(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarModels = assetHostService.getSimilarModels(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarModels,
                    model -> model,
                    model -> model
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 架构自动补全
     */
    @GetMapping("/auto_complete/architecture")
    public ResponseWrap<?> autoCompleteArchitecture(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarArchitectures = assetHostService.getSimilarArchitectures(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarArchitectures,
                    architecture -> architecture,
                    architecture -> architecture
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 系统名称自动补全
     */
    @GetMapping("/auto_complete/system_name")
    public ResponseWrap<?> autoCompleteSystemName(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarSystemNames = assetHostService.getSimilarSystemNames(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarSystemNames,
                    systemName -> systemName,
                    systemName -> systemName
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * 系统版本自动补全
     */
    @GetMapping("/auto_complete/system_version")
    public ResponseWrap<?> autoCompleteSystemVersion(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarSystemVersions = assetHostService.getSimilarSystemVersions(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarSystemVersions,
                    systemVersion -> systemVersion,
                    systemVersion -> systemVersion
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    /**
     * CPU型号自动补全
     */
    @GetMapping("/auto_complete/cpu_model")
    public ResponseWrap<?> autoCompleteCpuModel(@RequestParam(value = "term", required = false) String term) {
        try {
            List<String> similarCpuModels = assetHostService.getSimilarCpuModels(term);
            Map<String, List<Map<String, String>>> result = new HashMap<>();
            result.put("options", CommonUtil.createOptions(similarCpuModels,
                    cpuModel -> cpuModel,
                    cpuModel -> cpuModel
            ));
            return ResponseWrap.success(result);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

}
