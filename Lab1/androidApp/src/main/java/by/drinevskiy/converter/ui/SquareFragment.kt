package by.drinevskiy.converter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import by.drinevskiy.converter.ui.databinding.FragmentSquareBinding
import by.drinevskiy.converter.viewmodel.SharedViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SquareFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SquareFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
    private var _binding: FragmentSquareBinding? = null
    private val binding get() = _binding!!
    private val vm: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm.clearAll()
        _binding = FragmentSquareBinding.inflate(layoutInflater, container, false)
        vm.number1.observe(viewLifecycleOwner, Observer {
            binding.editText1.setText(it)
        })
        vm.number2.observe(viewLifecycleOwner, Observer {
            binding.editText2.setText(it)
        })
        binding.editText1.setOnFocusChangeListener { view, hasFocus ->
            vm.changeFocus1(hasFocus)
        }
        binding.editText2.setOnFocusChangeListener { view, hasFocus ->
            vm.changeFocus2(hasFocus)
        }
        val view = binding.root
        return view
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_square, container, false)
    }

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
//         * @return A new instance of fragment SquareFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            SquareFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}