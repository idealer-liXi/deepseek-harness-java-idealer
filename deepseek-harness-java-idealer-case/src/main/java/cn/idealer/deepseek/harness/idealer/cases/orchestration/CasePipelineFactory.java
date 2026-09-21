package cn.idealer.deepseek.harness.idealer.cases.orchestration;

public interface CasePipelineFactory<I, C, O> {
    CasePipeline<I, C, O> pipeline();
}
