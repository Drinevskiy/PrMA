package by.drinevskiy.converter.ui

//import androidx.fragment.app.
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.drinevskiy.converter.android.databinding.FragmentKeyboardBinding
import by.drinevskiy.converter.viewmodel.CommonViewModel


class KeyboardFragment : Fragment() {
    private var _binding: FragmentKeyboardBinding? = null
    private val binding get() = _binding!!
    private val vm: CommonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKeyboardBinding.inflate(inflater, container, false)
        binding.btn0.setOnClickListener {
            vm.pressNumber(binding.btn0.text.toString())
        }
        binding.btn1.setOnClickListener {
            vm.pressNumber(binding.btn1.text.toString())
        }
        binding.btn2.setOnClickListener {
            vm.pressNumber(binding.btn2.text.toString())
        }
        binding.btn3.setOnClickListener {
            vm.pressNumber(binding.btn3.text.toString())
        }
        binding.btn4.setOnClickListener {
            vm.pressNumber(binding.btn4.text.toString())
        }
        binding.btn5.setOnClickListener {
            vm.pressNumber(binding.btn5.text.toString())
        }
        binding.btn6.setOnClickListener {
            vm.pressNumber(binding.btn6.text.toString())
        }
        binding.btn7.setOnClickListener {
            vm.pressNumber(binding.btn7.text.toString())
        }
        binding.btn8.setOnClickListener {
            vm.pressNumber(binding.btn8.text.toString())
        }
        binding.btn9.setOnClickListener {
            vm.pressNumber(binding.btn9.text.toString())
        }
        binding.btnPoint.setOnClickListener {
            vm.pressNumber(binding.btnPoint.text.toString())
        }
        binding.btnClear.setOnClickListener {
            vm.deleteNumber()
        }
        val view = binding.root
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}