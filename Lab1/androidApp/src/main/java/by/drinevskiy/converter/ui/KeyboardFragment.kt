package by.drinevskiy.converter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import androidx.fragment.app.
import androidx.fragment.app.activityViewModels
import by.drinevskiy.converter.ui.databinding.FragmentKeyboardBinding
import by.drinevskiy.converter.viewmodel.SharedViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KeyboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KeyboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
    private var _binding: FragmentKeyboardBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private val vm: SharedViewModel by activityViewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKeyboardBinding.inflate(layoutInflater, container, false)
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
        // Inflate the layout for this fragment
        val view = binding.root
        return view
//        return inflater.inflate(R.layout.fragment_keyboard, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        vm = ViewModelProvider(requireActivity()).get(LengthViewModel::class.java)
////        Log.i("LengthFragment", "Keyboard created")
//
//        //listener onClick
//
//    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment KeyboardFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            KeyboardFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}