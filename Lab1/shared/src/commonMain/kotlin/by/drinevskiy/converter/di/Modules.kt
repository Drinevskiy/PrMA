package by.drinevskiy.converter.di

import by.drinevskiy.converter.viewmodel.CommonViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
    single { CommonViewModel() }
//    viewModelOf(::CommonViewModel)
}