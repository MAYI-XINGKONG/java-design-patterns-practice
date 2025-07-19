package com.niudada.level3.approvalflow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ApprovalFlowTest {

    @Test
    public void testFlowStructure() {
        ApprovalComponent flow = ApprovalService.buildApprovalFlow();
        log.info("审批流程结构：");
        flow.print("");
    }

    /**
     * 模拟通过审批
     */
    @Test
    public void testPassFlow() {
        ApprovalComponent flow = ApprovalService.buildApprovalFlow();
        log.info("审批流程构建树：");

        ApprovalContext context = new ApprovalContext()
                .addApprover("项目经理", true)
                .addApprover("财务专员", true)
                .addApprover("财务主管", true)
                .addApprover("技术负责人", true)
                .addApprover("架构师", true);

        boolean result = flow.approve(context, "");
        log.info("审批结果：{}", result);
    }

    /**
     * 模拟通过审批
     */
    @Test
    public void testFailedFlow() {
        ApprovalComponent flow = ApprovalService.buildApprovalFlow();
        log.info("审批流程构建树：");

        ApprovalContext context = new ApprovalContext()
                .addApprover("项目经理", true)
                .addApprover("财务专员", true)
                .addApprover("财务主管", false)
                .addApprover("技术负责人", true)
                .addApprover("架构师", true);

        boolean result = flow.approve(context, "");
        log.info("审批结果：{}", result);
    }
}
