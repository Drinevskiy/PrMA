package by.drinevskiy.converter.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import by.drinevskiy.converter.android.databinding.FragmentSquareBinding
import by.drinevskiy.converter.utils.launchUntilPaused
import by.drinevskiy.converter.viewmodel.CommonViewModel
import by.drinevskiy.converter.viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    private val vm: CommonViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm.clearAll()
        _binding = FragmentSquareBinding.inflate(inflater, container, false)
        lifecycleScope.launchUntilPaused(this){
            vm.state.collect { state ->
                binding.editText1.setText(state.number1)
                binding.editText2.setText(state.number2)
            }
        }
        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                vm.selectUnit1(parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, если ничего не выбрано (можно оставить пустым)
            }
        }
        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                vm.selectUnit2(parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, если ничего не выбрано (можно оставить пустым)
            }
        }
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
}