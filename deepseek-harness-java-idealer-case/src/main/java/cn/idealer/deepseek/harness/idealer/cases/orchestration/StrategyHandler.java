package cn.idealer.deepseek.harness.idealer.cases.orchestration;

public interface StrategyHandler<I, C, O> {
    O apply(I requestParameter, C dynamicContext) throws Exception;
}
