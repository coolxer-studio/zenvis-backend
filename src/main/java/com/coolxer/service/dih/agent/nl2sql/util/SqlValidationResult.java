package com.coolxer.service.dih.agent.nl2sql.util;

/**
 * SQL 安全校验结果
 */
public class SqlValidationResult {

    private final boolean valid;
    private final String rejectionReason;

    private SqlValidationResult(boolean valid, String rejectionReason) {
        this.valid = valid;
        this.rejectionReason = rejectionReason;
    }

    public static SqlValidationResult ok() {
        return new SqlValidationResult(true, null);
    }

    public static SqlValidationResult reject(String reason) {
        return new SqlValidationResult(false, reason);
    }

    public boolean isValid() {
        return valid;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }
}
