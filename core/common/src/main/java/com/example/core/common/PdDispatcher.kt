package com.example.core.common

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME


@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val pdDispatcher: PdDispatchers)

enum class PdDispatchers {
    Default,
    IO,
}