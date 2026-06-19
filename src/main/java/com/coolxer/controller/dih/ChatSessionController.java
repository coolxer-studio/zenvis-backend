package com.coolxer.controller.dih;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.controller.BaseController;
import com.coolxer.dao.mysql.entity.ChatSession;
import com.coolxer.dao.mysql.entity.User;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.base.vo.ResponseWrap;
import com.coolxer.model.dih.dto.ChatSessionDto;
import com.coolxer.model.dih.dto.ChatSessionSearchDto;
import com.coolxer.model.dih.vo.ChatSessionVo;
import com.coolxer.service.dih.ChatSessionService;
import com.coolxer.utils.DateUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会话管理
 */
@Api
@Slf4j
@RestController
@RequestMapping("/api/v1/dih/chat-session")
public class ChatSessionController extends BaseController {

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private ChatSessionService chatSessionService;

    @PostMapping({"/add"})
    public ResponseWrap<?> add(@RequestBody ChatSessionDto chatSessionDto) {
        try {
            User currentUser = getSessionUser();
            if (chatSessionService.create(chatSessionDto, currentUser) != null) {
                return ResponseWrap.success("创建成功");
            } else {
                return ResponseWrap.fail(ResultCodeEnum.UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseWrap<?> delete(@PathVariable("id") Long id) {
        try {
            User currentUser = getSessionUser();
            chatSessionService.delete(id, currentUser);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @DeleteMapping({"/bulk/{ids}"})
    public ResponseWrap<?> bulkDelete(@PathVariable("ids") List<Long> ids) {
        try {
            User currentUser = getSessionUser();
            chatSessionService.deleteByIds(ids, currentUser);
            return ResponseWrap.success("删除成功");
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @PostMapping({"/{id}/update"})
    public ResponseWrap<?> update(@PathVariable("id") Long id, @RequestBody ChatSessionDto chatSessionDto) {
        try {
            User currentUser = getSessionUser();
            if (chatSessionService.update(id, chatSessionDto, currentUser)) {
                return ResponseWrap.success("修改成功");
            } else
                return ResponseWrap.fail();
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/list/pin"})
    public ResponseWrap<?> listPin() {
        try {
            User currentUser = getSessionUser();
            List<ChatSessionVo> chatSessionVoList = chatSessionService.getPinList(currentUser);
            return ResponseWrap.success(chatSessionVoList);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/list"})
    public ResponseWrap<?> list(ChatSessionSearchDto chatSessionSearchDto) {
        try {
            User currentUser = getSessionUser();
            PageRowsVo<ChatSessionVo> pageDataVo = chatSessionService.getPageList(chatSessionSearchDto, currentUser);
            return ResponseWrap.success(pageDataVo);
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    @GetMapping({"/{id}/view"})
    public ResponseWrap<ChatSessionVo> query(@PathVariable("id") Long id) {
        try {
            User currentUser = getSessionUser();
            ChatSessionVo chatSessionVo = chatSessionService.info(id, currentUser);
            if (chatSessionVo == null) {
                return ResponseWrap.fail();
            } else {
                return ResponseWrap.success(chatSessionVo);
            }
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

    private static final String PROLOGUE_DEFAULT = "我是数智助手（X-Sage），可以解答系统相关运营问题，有什么问题尽管提问吧！";
    private static final String PROLOGUE_AGENT_PLUGIN = "我是插件智能体，可以帮助用户快速构建插件应用。\\n" +
            " 生成元数据配置、数推服务配置、UI可视化配置、扩展接口及菜单,支持预览，用户确认后生成插件并导出。\\n" +
            " 请提供给我插件的必要信息，如名称、包名、用途等！";
    private static final String PROLOGUE_AGENT_INSPECT = "我是巡检智能体，专注于多源日志数据的智能分析与可视化呈现。\\n" +
            " 通过全局随机抽样与重点数据智能挖掘，实时提供精准的数据统计、查询及可视化服务。\\n" +
            " 我将根据用户提供的实体、字段和统计维度，自动调用最优查询接口并智能匹配最合适的图表组件，确保数据洞察清晰直观。";
    private static final String PROLOGUE_AGENT_ANALYSIS = "我是研判智能体，专注于风险事件的深度分析与等级评估。\\n" +
            " 通过数据聚合、情报关联、规则匹配及动态执行等多维度研判手段，精准评估风险等级合理性。\\n" +
            " 所有研判过程均调用外部工具进行证据链验证，所有分析依据与取证结果将完整存档，确保研判结论可追溯、可复现。";
    private static final String PROLOGUE_AGENT_DISPOSE = "我是策略智能体，负责系统策略的全生命周期管理。\\n" +
            " 涵盖探针数据采集、动态标记引擎、处置响应、设备指纹、风险评定、数据推送及可视化等策略配置。\\n" +
            " 所有策略变更需经管理员审批后生效，确保系统配置安全可控、合规有效。";
    private static final String PROLOGUE_AGENT_CHECK = "我是检验智能体，专注于问题闭环验证与效果评估。\\n" +
            " 针对巡检发现的问题、研判结果及策略调整，通过自动化工具进行效果核验。\\n" +
            " 未通过验证的问题将自动生成结构化工单并推送至指定负责人，确保问题解决过程可追踪、可闭环。";
    private static final String PROLOGUE_AGENT_REPORT = "我是报告智能体，专注于高效生成专业分析报告。\\n" +
            " 通过智能编辑器，快速整合分析过程中的数据、图表与结论，实现内容自动生成与文案优化。\\n" +
            " 支持一键导入分析素材，助您快速产出结构清晰、内容详实的高质量分析报告。";

    @GetMapping({"/{sessionId}/session"})
    public ResponseWrap<ChatSessionVo> sessionInfo(@PathVariable("sessionId") String sessionId, @RequestParam(value = "type", required = false) String type) {
        try {
            User currentUser = getSessionUser();
            ChatSession chatSession = chatSessionService.getChatSessionBySessionId(sessionId, currentUser);
            if (chatSession == null) {
                // 返回默认会话开头语模版
                chatSession = new ChatSession();
                chatSession.setTitle("新建会话");
                chatSession.setSessionId(sessionId);
                chatSession.setType(type);
                switch (type) {
                    case "ask":
                        chatSession.setMessages("[{\"sender\":\"ai\",\"content\":\"%s\",\"time\":\"%s\"}]".formatted(PROLOGUE_DEFAULT, DateUtil.getCurrentDateTime()));
                        break;
                    case "agent_inspect":
                        chatSession.setMessages("[{\"sender\":\"ai\",\"content\":\"%s\",\"time\":\"%s\"}]".formatted(PROLOGUE_AGENT_INSPECT, DateUtil.getCurrentDateTime()));
                        break;
                    case "agent_analysis":
                        chatSession.setMessages("[{\"sender\":\"ai\",\"content\":\"%s\",\"time\":\"%s\"}]".formatted(PROLOGUE_AGENT_ANALYSIS, DateUtil.getCurrentDateTime()));
                        break;
                    case "agent_dispose":
                        chatSession.setMessages("[{\"sender\":\"ai\",\"content\":\"%s\",\"time\":\"%s\"}]".formatted(PROLOGUE_AGENT_DISPOSE, DateUtil.getCurrentDateTime()));
                        break;
                    case "agent_check":
                        chatSession.setMessages("[{\"sender\":\"ai\",\"content\":\"%s\",\"time\":\"%s\"}]".formatted(PROLOGUE_AGENT_CHECK, DateUtil.getCurrentDateTime()));
                        break;
                    case "agent_report":
                        chatSession.setMessages("[{\"sender\":\"ai\",\"content\":\"%s\",\"time\":\"%s\"}]".formatted(PROLOGUE_AGENT_REPORT, DateUtil.getCurrentDateTime()));
                        break;
                    case "agent_plugin":
                        chatSession.setMessages("[{\"sender\":\"ai\",\"content\":\"%s\",\"time\":\"%s\"}]".formatted(PROLOGUE_AGENT_PLUGIN, DateUtil.getCurrentDateTime()));
                        break;
                    default:
                        chatSession.setMessages("[{\"sender\":\"ai\",\"content\":\"%s\",\"time\":\"%s\"}]".formatted(PROLOGUE_DEFAULT, DateUtil.getCurrentDateTime()));
                        break;
                }
            }
            return ResponseWrap.success(new ChatSessionVo(chatSession));
        } catch (Exception e) {
            return ResponseWrap.fail(e);
        }
    }

}
