package com.celikin.llama2.wrapper


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class InferenceRunnerManager(
    callback: InferenceRunner.InferenceCallback,
    private val folderPath: String,
    private val checkpointFileName: String,
    private val tokenizerFileName: String,
    private val ompThreads: Int = DEFAULT_OMP_THREADS
) {
    private val applicationScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        InferenceRunner.setInferenceCallback(callback)
    }

    fun run(
        prompt: String = "",
        temperature: Float = DEFAULT_TEMPERATURE,
        steps: Int = DEFAULT_STEPS,
        topp: Float = DEFAULT_TOPP
    ) {
        applicationScope.launch {
            InferenceRunner.run(
                checkpoint = "$folderPath/$checkpointFileName",
                tokenizer = "$folderPath/$tokenizerFileName",
                temperature = temperature,
                steps = steps,
                topp = topp,
                prompt = prompt,
                ompthreads = ompThreads
            )
        }
    }

    companion object {
        private const val DEFAULT_OMP_THREADS = 4
        private const val DEFAULT_TEMPERATURE = 1.0f
        private const val DEFAULT_STEPS = 256
        private const val DEFAULT_TOPP = 0.9f
    }
}