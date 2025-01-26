package by.drinevskiy.converter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel(){
    private var _number1 = MutableLiveData<String>("")
    private var _number2 = MutableLiveData<String>("")
    private var _unit1 = MutableLiveData<String>()
    private var _unit2 = MutableLiveData<String>()
    private var _isFocus1 = MutableLiveData<Boolean>(false)
    private var _isFocus2 = MutableLiveData<Boolean>(false)
    val number1: LiveData<String> get() = _number1
    val number2: LiveData<String> get() = _number2
    val unit1: LiveData<String> get() = _unit1
    val unit2: LiveData<String> get() = _unit2
    val isFocus1: LiveData<Boolean> get() = _isFocus1
    val isFocus2: LiveData<Boolean> get() = _isFocus2

    fun pressNumber(msg: String) {
//        Log.i("LengthViewModel", "Press " + msg)
        if (isFocus1.value == true) {
            _number1.value += msg
        } else if (isFocus2.value == true) {
            _number2.value += msg
        }
    }

    fun deleteNumber() {
        if (isFocus1.value == true) {
            _number1.value = _number1.value?.dropLast(1)
        } else if (isFocus2.value == true) {
            _number2.value = _number2.value?.dropLast(1)
        }
    }

    fun changeFocus1(isFocus: Boolean){
//        Log.i("LengthViewModel", "Change focus1")
        _isFocus1.value = isFocus
    }

    fun changeFocus2(isFocus: Boolean){
        _isFocus2.value = isFocus
    }

    fun clearAll(){
        _number1.value = ""
        _number2.value = ""
        _isFocus1.value = false
        _isFocus2.value = false
    }
}