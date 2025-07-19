package com.niudada.level3.approvalflow;

import java.util.HashMap;
import java.util.Map;

public class ApprovalContext {
    private final Map<String, Boolean> approvalResults = new HashMap<>();

    public ApprovalContext addApprover(String name, boolean approved) {
        approvalResults.put(name, approved);
        return this;
    }

    public boolean approveBy(String name) {
        return approvalResults.getOrDefault(name, false);
    }
}
