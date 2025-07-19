package com.niudada.level3.approvalflow;

public class ApprovalService {
    public static ApprovalComponent buildApprovalFlow() {
        return new ApprovalGroup("公司审批流程")
                .add(new ApprovalNode("项目经理"))
                .add(new ApprovalGroup("财务审批")
                        .add(new ApprovalNode("财务专员"))
                        .add(new ApprovalNode("财务主管")))
                .add(new ApprovalGroup("技术审批")
                        .add(new ApprovalNode("技术负责人"))
                        .add(new ApprovalNode("架构师")));
    }
}
