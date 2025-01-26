package by.drinevskiy.converter

import android.app.Application
import by.drinevskiy.converter.di.initKoin
import org.koin.android.ext.koin.androidContext

class ConverterApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ConverterApplication)
        }
    }
}