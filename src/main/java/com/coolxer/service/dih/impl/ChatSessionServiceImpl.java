package com.coolxer.service.dih.impl;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.commons.exception.ApiException;
import com.coolxer.dao.mysql.entity.ChatSession;
import com.coolxer.dao.mysql.entity.User;
import com.coolxer.dao.mysql.repository.ChatSessionRepository;
import com.coolxer.model.base.vo.PageRowsVo;
import com.coolxer.model.dih.dto.ChatSessionDto;
import com.coolxer.model.dih.dto.ChatSessionSearchDto;
import com.coolxer.model.dih.vo.ChatSessionVo;
import com.coolxer.service.dih.ChatSessionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ChatSessionServiceImpl implements ChatSessionService {

    @Autowired
    private ChatSessionRepository chatSessionRepository;

    @Override
    public List<ChatSessionVo> findAll() {
        return chatSessionRepository.findAll().stream().map(ChatSessionVo::new).toList();
    }

    @Override
    public ChatSession create(ChatSessionDto chatSessionDto, User currentUser) {
        if (currentUser == null) {
            // 不支持删除
            throw new ApiException(ResultCodeEnum.NO_AUTHORITY);
        }
        checkCreateOrUpdate(chatSessionDto);
        ChatSession chatSession = new ChatSession();
        chatSession.updateFromDto(chatSessionDto);
        chatSession.setCreateBy(currentUser.getId());
        return chatSessionRepository.save(chatSession);
    }

    @Override
    public Boolean update(Long id, ChatSessionDto chatSessionDto, User currentUser) {
        try {
            Optional<ChatSession> optionalChatSession = chatSessionRepository.findById(id);
            if (optionalChatSession.isPresent() && optionalChatSession.get().getCreateBy() == currentUser.getId()) {
                ChatSession chatSession = optionalChatSession.get();
                chatSession.updateFromDto(chatSessionDto);
                chatSessionRepository.save(chatSession);
                return true;
            } else {
                // 不支持删除
                throw new ApiException(ResultCodeEnum.NO_AUTHORITY);
            }
        } catch (Exception e) {
            log.error("更新对象失败, id: {}", id, e);
            return false;
        }
    }

    @Override
    public void delete(Long id, User currentUser) {
        ChatSession chatSession = chatSessionRepository.findById(id).orElse(null);
        if (chatSession != null) {
            if (chatSession.getCreateBy() != currentUser.getId()) {
                // 不支持删除
                throw new ApiException(ResultCodeEnum.NO_AUTHORITY);
            } else {
                chatSessionRepository.deleteById(id);
            }
        }
    }

    @Override
    public void deleteByIds(List<Long> ids, User currentUser) {
        for (Long id : ids) {
            delete(id, currentUser);
        }
    }

    @Override
    public ChatSessionVo info(Long id, User currentUser) {
        try {
            Optional<ChatSession> optionalChatSession = chatSessionRepository.findById(id);
            if (optionalChatSession.isPresent() && optionalChatSession.get().getCreateBy() != currentUser.getId()) {
                return null;
            }
            return optionalChatSession.map(ChatSessionVo::new).orElse(null);
        } catch (Exception e) {
            log.error("获取对象失败, id: {}", id, e);
            return null;
        }
    }

    @Override
    public List<ChatSessionVo> getPinList(User currentUser) {
        try {
            List<ChatSession> chatSessionList = chatSessionRepository.findPinChatSessionByUser(currentUser.getId());
            return chatSessionList.stream().map(ChatSessionVo::new).toList();
        } catch (Exception e) {
            log.error("查询失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public PageRowsVo<ChatSessionVo> getPageList(ChatSessionSearchDto chatSessionSearchDto, User currentUser) {
        try {
            Pageable pageable = PageRequest.of(chatSessionSearchDto.getPage() - 1, chatSessionSearchDto.getPerPage());
            Page<ChatSession> byPage;
            byPage = chatSessionRepository.findByPage(pageable, chatSessionSearchDto.getTitle(), currentUser.getId());
            return new PageRowsVo<>(
                    byPage.getContent().stream().map(ChatSessionVo::new).toList(),
                    byPage.getTotalElements()
            );
        } catch (Exception e) {
            log.error("分页查询失败", e);
            return new PageRowsVo<>(Collections.emptyList(), 0L);
        }
    }

    @Override
    public ChatSession getChatSessionBySessionId(String chatId, User currentUser) {
        Optional<ChatSession> optionalChatSession = chatSessionRepository.findBySessionId(chatId);
        if (optionalChatSession.isPresent() && optionalChatSession.get().getCreateBy() == currentUser.getId()) {
            return optionalChatSession.get();
        }
        return null;
    }

    private static void checkCreateOrUpdate(ChatSessionDto chatSessionDto) {
        if (StringUtils.isEmpty(chatSessionDto.getTitle()) || StringUtils.isEmpty(chatSessionDto.getMessages())) {
            throw new ApiException(ResultCodeEnum.FIELD_IS_EMPTY);
        }
    }

}
