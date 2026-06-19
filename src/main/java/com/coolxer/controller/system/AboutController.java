package com.coolxer.controller.system;

import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.base.vo.SingleValueVo;
import com.coolxer.model.system.dto.SystemInfoDto;
import com.coolxer.model.system.vo.SystemInfoVo;
import com.coolxer.service.system.SystemInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 系统信息
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/system/about")
public class AboutController {


    @Autowired
    private SystemInfoService systemInfoService;

    @GetMapping({"/info"})
    @ApiOperation(value = "关于系统信息", notes = "关于系统信息")
    public ResponseWrap<SystemInfoVo> getSystemInfo() {
        SystemInfoVo systemInfoVo = systemInfoService.getSystemInfo();
        return ResponseWrap.success(systemInfoVo);
    }

    @PutMapping({"/info/update"})
    @ApiOperation(value = "修改系统信息", notes = "修改系统信息")
    public ResponseWrap<Void> updateSystemInfo(@RequestBody SystemInfoDto systemInfoDto) {
        boolean result = systemInfoService.update(systemInfoDto);
        if (result) {
            return ResponseWrap.success();
        } else {
            return ResponseWrap.fail();
        }
    }

    @PostMapping({"/icon/upload"})
    @ApiOperation(value = "上传icon图标", notes = "上传icon图标并保存到服务器")
    public ResponseWrap<SingleValueVo> uploadIcon(@RequestParam("file") MultipartFile file) {
        if (systemInfoService.update(file, SystemInfoService.SYSTEM_ICON_FILENAME)) {
            return ResponseWrap.success();
        } else {
            return ResponseWrap.fail();
        }
    }

    @PostMapping({"/logo/upload"})
    @ApiOperation(value = "上传系统图标", notes = "上传系统图标并保存到服务器")
    public ResponseWrap<SingleValueVo> uploadLogo(@RequestParam("file") MultipartFile file) {
        if (systemInfoService.update(file, SystemInfoService.SYSTEM_LOGO_FILENAME)) {
            return ResponseWrap.success();
        } else {
            return ResponseWrap.fail();
        }
    }

    @PostMapping({"/banner/upload"})
    @ApiOperation(value = "上传banner图标", notes = "上传banner图标并保存到服务器")
    public ResponseWrap<SingleValueVo> uploadBanner(@RequestParam("file") MultipartFile file) {
        if (systemInfoService.update(file, SystemInfoService.SYSTEM_BANNER_FILENAME)) {
            return ResponseWrap.success();
        } else {
            return ResponseWrap.fail();
        }
    }


}
