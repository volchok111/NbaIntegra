package com.metra.domain.utils

sealed interface UseCase<in I, out O>

interface SynchronousUseCase<in I, out O> : UseCase<I, O> {
    operator fun invoke(input: I): O
}

interface SuspendUseCase<in I, out O> : UseCase<I, O> {
    suspend operator fun invoke(input: I): O
}

operator fun <O> SynchronousUseCase<Unit, O>.invoke(): O = invoke(Unit)

suspend operator fun <O> SuspendUseCase<Unit, O>.invoke(): O = invoke(Unit)
