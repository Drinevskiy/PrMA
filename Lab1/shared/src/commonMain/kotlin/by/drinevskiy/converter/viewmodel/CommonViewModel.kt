package by.drinevskiy.converter.viewmodel

//package by.drinevskiy.converter.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

val length_array = listOf<String>("ml", "km", "ft")
val square_array = listOf<String>("ac", "km^2", "ha")
val volume_array = listOf<String>("l", "gal", "ft^3")

class CommonViewModel : ViewModel(){
    private val _state = MutableStateFlow(CommonUiState())
    val state = _state

//    private fun convertDoubleToString(value: String): String {
//        return try {
//            if (value.isEmpty()) return value
//
//            // Remove trailing zero if the number is a decimal
//            if (value.endsWith(".0")) {
//                value.dropLast(2)
//            } else if (value.endsWith('.')) {
//                value
//            } else {
//                val doubleValue = value.toDouble()
//                if (doubleValue % 1.0 == 0.0) {
//                    doubleValue.toLong().toString()
//                } else {
//                    value
//                }
//            }
//        } catch (e: NumberFormatException) {
//            e.printStackTrace()
//            value
//        }
//    }

    private fun updateValues(msg: String, isDelete: Boolean = false, isFirst: Boolean = true){
        if(isFirst) {
            _state.update {
                if(isDelete){
                    it.copy(
                        number1 = _state.value.number1.dropLast(1),
                    )
                }
                else {
                    it.copy(
                        number1 = _state.value.number1 + msg,
                    )
                }
            }
            val number = _state.value.number1.toDoubleOrNull()
            if (number != null) {
                var res = number
                if (_state.value.unit1 in length_array && _state.value.unit2 in length_array) {
                    res = when {
                        _state.value.unit1 == "ml" && _state.value.unit2 == "km" -> number * 1.60934
                        _state.value.unit1 == "ml" && _state.value.unit2 == "ft" -> number * 5280
                        _state.value.unit1 == "ml" && _state.value.unit2 == "ml" -> number
                        _state.value.unit1 == "km" && _state.value.unit2 == "km" -> number
                        _state.value.unit1 == "km" && _state.value.unit2 == "ft" -> number * 3280.84
                        _state.value.unit1 == "km" && _state.value.unit2 == "ml" -> number / 1.60934
                        _state.value.unit1 == "ft" && _state.value.unit2 == "km" -> number / 3280.84
                        _state.value.unit1 == "ft" && _state.value.unit2 == "ft" -> number
                        _state.value.unit1 == "ft" && _state.value.unit2 == "ml" -> number / 5280
                        else -> 0.0
                    }
                }
                if (_state.value.unit1 in square_array && _state.value.unit2 in square_array) {
                    res = when {
                        _state.value.unit1 == "ac" && _state.value.unit2 == "km^2" -> number * 0.00404686
                        _state.value.unit1 == "ac" && _state.value.unit2 == "ha" -> number / 2.47105
                        _state.value.unit1 == "ac" && _state.value.unit2 == "ac" -> number
                        _state.value.unit1 == "km^2" && _state.value.unit2 == "km^2" -> number
                        _state.value.unit1 == "km^2" && _state.value.unit2 == "ha" -> number * 100
                        _state.value.unit1 == "km^2" && _state.value.unit2 == "ac" -> number / 0.00404686
                        _state.value.unit1 == "ha" && _state.value.unit2 == "km^2" -> number / 100
                        _state.value.unit1 == "ha" && _state.value.unit2 == "ha" -> number
                        _state.value.unit1 == "ha" && _state.value.unit2 == "ac" -> number * 2.47105
                        else -> 0.0
                    }
                }
                if (_state.value.unit1 in volume_array && _state.value.unit2 in volume_array) {
                    res = when {
                        _state.value.unit1 == "l" && _state.value.unit2 == "gal" -> number / 3.78541
                        _state.value.unit1 == "l" && _state.value.unit2 == "ft^3" -> number / 28.3168
                        _state.value.unit1 == "l" && _state.value.unit2 == "l" -> number
                        _state.value.unit1 == "gal" && _state.value.unit2 == "gal" -> number
                        _state.value.unit1 == "gal" && _state.value.unit2 == "ft^3" -> number / 7.48052
                        _state.value.unit1 == "gal" && _state.value.unit2 == "l" -> number * 3.78541
                        _state.value.unit1 == "ft^3" && _state.value.unit2 == "gal" -> number * 7.48052
                        _state.value.unit1 == "ft^3" && _state.value.unit2 == "ft^3" -> number
                        _state.value.unit1 == "ft^3" && _state.value.unit2 == "l" -> number * 28.3168
                        else -> 0.0
                    }
                }
                _state.update {
                    it.copy(
                        number2 = res.toString(),
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        number2 = 0.0.toString(),
                    )
                }
            }
        }
        else{
            _state.update {
                if(isDelete){
                    it.copy(
                        number2 = _state.value.number2.dropLast(1),
                    )
                }
                else {
                    it.copy(
                        number2 = _state.value.number2 + msg,
                    )
                }
            }
            val number = _state.value.number2.toDoubleOrNull()
            if (number != null) {
                var res = number
                if (_state.value.unit1 in length_array && _state.value.unit2 in length_array) {
                    res = when {
                        _state.value.unit1 == "ml" && _state.value.unit2 == "km" -> number / 1.60934
                        _state.value.unit1 == "ml" && _state.value.unit2 == "ft" -> number / 5280
                        _state.value.unit1 == "ml" && _state.value.unit2 == "ml" -> number
                        _state.value.unit1 == "km" && _state.value.unit2 == "km" -> number
                        _state.value.unit1 == "km" && _state.value.unit2 == "ft" -> number / 3280.84
                        _state.value.unit1 == "km" && _state.value.unit2 == "ml" -> number * 1.60934
                        _state.value.unit1 == "ft" && _state.value.unit2 == "km" -> number * 3280.84
                        _state.value.unit1 == "ft" && _state.value.unit2 == "ft" -> number
                        _state.value.unit1 == "ft" && _state.value.unit2 == "ml" -> number * 5280
                        else -> 0.0
                    }
                }
                if (_state.value.unit1 in square_array && _state.value.unit2 in square_array) {
                    res = when {
                        _state.value.unit1 == "ac" && _state.value.unit2 == "km^2" -> number / 0.00404686
                        _state.value.unit1 == "ac" && _state.value.unit2 == "ha" -> number * 2.47105
                        _state.value.unit1 == "ac" && _state.value.unit2 == "ac" -> number
                        _state.value.unit1 == "km^2" && _state.value.unit2 == "km^2" -> number
                        _state.value.unit1 == "km^2" && _state.value.unit2 == "ha" -> number / 100
                        _state.value.unit1 == "km^2" && _state.value.unit2 == "ac" -> number * 0.00404686
                        _state.value.unit1 == "ha" && _state.value.unit2 == "km^2" -> number * 100
                        _state.value.unit1 == "ha" && _state.value.unit2 == "ha" -> number
                        _state.value.unit1 == "ha" && _state.value.unit2 == "ac" -> number / 2.47105
                        else -> 0.0
                    }
                }
                if (_state.value.unit1 in volume_array && _state.value.unit2 in volume_array) {
                    res = when {
                        _state.value.unit1 == "l" && _state.value.unit2 == "gal" -> number * 3.78541
                        _state.value.unit1 == "l" && _state.value.unit2 == "ft^3" -> number * 28.3168
                        _state.value.unit1 == "l" && _state.value.unit2 == "l" -> number
                        _state.value.unit1 == "gal" && _state.value.unit2 == "gal" -> number
                        _state.value.unit1 == "gal" && _state.value.unit2 == "ft^3" -> number * 7.48052
                        _state.value.unit1 == "gal" && _state.value.unit2 == "l" -> number / 3.78541
                        _state.value.unit1 == "ft^3" && _state.value.unit2 == "gal" -> number / 7.48052
                        _state.value.unit1 == "ft^3" && _state.value.unit2 == "ft^3" -> number
                        _state.value.unit1 == "ft^3" && _state.value.unit2 == "l" -> number / 28.3168
                        else -> 0.0
                    }
                }
                _state.update {
                    it.copy(
                        number1 = res.toString(),
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        number1 = 0.0.toString(),
                    )
                }
            }
        }
    }

    private fun changeUnit(unit: String, isFirst: Boolean = true){
        if(isFirst){
            _state.update { it.copy(
                unit1 = unit
            ) }
            val number = _state.value.number1.toDoubleOrNull()
            if (number != null) {
                var res = number
                if (_state.value.unit1 in length_array && _state.value.unit2 in length_array) {
                    res = when {
                        _state.value.unit1 == "ml" && _state.value.unit2 == "km" -> number * 1.60934
                        _state.value.unit1 == "ml" && _state.value.unit2 == "ft" -> number * 5280
                        _state.value.unit1 == "ml" && _state.value.unit2 == "ml" -> number
                        _state.value.unit1 == "km" && _state.value.unit2 == "km" -> number
                        _state.value.unit1 == "km" && _state.value.unit2 == "ft" -> number * 3280.84
                        _state.value.unit1 == "km" && _state.value.unit2 == "ml" -> number / 1.60934
                        _state.value.unit1 == "ft" && _state.value.unit2 == "km" -> number / 3280.84
                        _state.value.unit1 == "ft" && _state.value.unit2 == "ft" -> number
                        _state.value.unit1 == "ft" && _state.value.unit2 == "ml" -> number / 5280
                        else -> 0.0
                    }
                }
                if (_state.value.unit1 in square_array && _state.value.unit2 in square_array) {
                    res = when {
                        _state.value.unit1 == "ac" && _state.value.unit2 == "km^2" -> number * 0.00404686
                        _state.value.unit1 == "ac" && _state.value.unit2 == "ha" -> number / 2.47105
                        _state.value.unit1 == "ac" && _state.value.unit2 == "ac" -> number
                        _state.value.unit1 == "km^2" && _state.value.unit2 == "km^2" -> number
                        _state.value.unit1 == "km^2" && _state.value.unit2 == "ha" -> number * 100
                        _state.value.unit1 == "km^2" && _state.value.unit2 == "ac" -> number / 0.00404686
                        _state.value.unit1 == "ha" && _state.value.unit2 == "km^2" -> number / 100
                        _state.value.unit1 == "ha" && _state.value.unit2 == "ha" -> number
                        _state.value.unit1 == "ha" && _state.value.unit2 == "ac" -> number * 2.47105
                        else -> 0.0
                    }
                }
                if (_state.value.unit1 in volume_array && _state.value.unit2 in volume_array) {
                    res = when {
                        _state.value.unit1 == "l" && _state.value.unit2 == "gal" -> number / 3.78541
                        _state.value.unit1 == "l" && _state.value.unit2 == "ft^3" -> number / 28.3168
                        _state.value.unit1 == "l" && _state.value.unit2 == "l" -> number
                        _state.value.unit1 == "gal" && _state.value.unit2 == "gal" -> number
                        _state.value.unit1 == "gal" && _state.value.unit2 == "ft^3" -> number / 7.48052
                        _state.value.unit1 == "gal" && _state.value.unit2 == "l" -> number * 3.78541
                        _state.value.unit1 == "ft^3" && _state.value.unit2 == "gal" -> number * 7.48052
                        _state.value.unit1 == "ft^3" && _state.value.unit2 == "ft^3" -> number
                        _state.value.unit1 == "ft^3" && _state.value.unit2 == "l" -> number * 28.3168
                        else -> 0.0
                    }
                }
                _state.update {
                    it.copy(
                        number2 = res.toString(),
                    )
                }
            }
        }
        else{
            _state.update { it.copy(
                unit2 = unit
            ) }
            val number = _state.value.number2.toDoubleOrNull()
            if (number != null) {
                var res = number
                if (_state.value.unit1 in length_array && _state.value.unit2 in length_array) {
                    res = when {
                        _state.value.unit1 == "ml" && _state.value.unit2 == "km" -> number / 1.60934
                        _state.value.unit1 == "ml" && _state.value.unit2 == "ft" -> number / 5280
                        _state.value.unit1 == "ml" && _state.value.unit2 == "ml" -> number
                        _state.value.unit1 == "km" && _state.value.unit2 == "km" -> number
                        _state.value.unit1 == "km" && _state.value.unit2 == "ft" -> number / 3280.84
                        _state.value.unit1 == "km" && _state.value.unit2 == "ml" -> number * 1.60934
                        _state.value.unit1 == "ft" && _state.value.unit2 == "km" -> number * 3280.84
                        _state.value.unit1 == "ft" && _state.value.unit2 == "ft" -> number
                        _state.value.unit1 == "ft" && _state.value.unit2 == "ml" -> number * 5280
                        else -> 0.0
                    }
                }
                if (_state.value.unit1 in square_array && _state.value.unit2 in square_array) {
                    res = when {
                        _state.value.unit1 == "ac" && _state.value.unit2 == "km^2" -> number / 0.00404686
                        _state.value.unit1 == "ac" && _state.value.unit2 == "ha" -> number * 2.47105
                        _state.value.unit1 == "ac" && _state.value.unit2 == "ac" -> number
                        _state.value.unit1 == "km^2" && _state.value.unit2 == "km^2" -> number
                        _state.value.unit1 == "km^2" && _state.value.unit2 == "ha" -> number / 100
                        _state.value.unit1 == "km^2" && _state.value.unit2 == "ac" -> number * 0.00404686
                        _state.value.unit1 == "ha" && _state.value.unit2 == "km^2" -> number * 100
                        _state.value.unit1 == "ha" && _state.value.unit2 == "ha" -> number
                        _state.value.unit1 == "ha" && _state.value.unit2 == "ac" -> number / 2.47105
                        else -> 0.0
                    }
                }
                if (_state.value.unit1 in volume_array && _state.value.unit2 in volume_array) {
                    res = when {
                        _state.value.unit1 == "l" && _state.value.unit2 == "gal" -> number * 3.78541
                        _state.value.unit1 == "l" && _state.value.unit2 == "ft^3" -> number * 28.3168
                        _state.value.unit1 == "l" && _state.value.unit2 == "l" -> number
                        _state.value.unit1 == "gal" && _state.value.unit2 == "gal" -> number
                        _state.value.unit1 == "gal" && _state.value.unit2 == "ft^3" -> number * 7.48052
                        _state.value.unit1 == "gal" && _state.value.unit2 == "l" -> number / 3.78541
                        _state.value.unit1 == "ft^3" && _state.value.unit2 == "gal" -> number / 7.48052
                        _state.value.unit1 == "ft^3" && _state.value.unit2 == "ft^3" -> number
                        _state.value.unit1 == "ft^3" && _state.value.unit2 == "l" -> number / 28.3168
                        else -> 0.0
                    }
                }
                _state.update {
                    it.copy(
                        number1 = res.toString(),
                    )
                }
            }
        }
    }

    fun pressNumber(msg: String) {
        if (_state.value.isFocus1) {
            updateValues(msg)
        } else if (_state.value.isFocus2) {
            updateValues(msg, isFirst = false)
        }
    }

    fun deleteNumber() {
        if (_state.value.isFocus1) {
            updateValues(".", true)
        } else if (_state.value.isFocus2) {
            updateValues(".", true, false)
        }
    }

    fun changeFocus1(isFocus: Boolean){
        _state.update { it.copy(
            isFocus1 = isFocus
        ) }
    }

    fun changeFocus2(isFocus: Boolean){
        _state.update { it.copy(
            isFocus2 = isFocus
        ) }
    }

    fun selectUnit1(unit: String){
        changeUnit(unit)
    }

    fun selectUnit2(unit: String){
        changeUnit(unit, false)
    }

    fun clearAll(){
        _state.update { CommonUiState() }
    }
}