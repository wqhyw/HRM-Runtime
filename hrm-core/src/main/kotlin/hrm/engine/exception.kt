package hrm.engine

/**
 *  @author wqhyw
 *  @date 2020-08-21 18:13:54
 */
open class EngineExecutionException : RuntimeException()

class StepsTooMuchException : EngineExecutionException()

class NoSuchLabelException : EngineExecutionException()

class FloorCannotDereferenceException : EngineExecutionException()

class FloorNotCalculableException : EngineExecutionException()

class PcNotCalculableException : EngineExecutionException()

class PcAndTileCannotCompareException : EngineExecutionException()

class PcIsNullException : EngineExecutionException()

