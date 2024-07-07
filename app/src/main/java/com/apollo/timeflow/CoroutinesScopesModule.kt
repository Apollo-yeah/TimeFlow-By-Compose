package com.apollo.timeflow

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModule {
    @Provides
    fun providesCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Provides
    fun providesCoroutineContext(): CoroutineContext = CoroutineScope(SupervisorJob() + Dispatchers.IO).coroutineContext
}