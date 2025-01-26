package by.drinevskiy.converter.ui

//import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import by.drinevskiy.converter.android.databinding.FragmentLengthBinding
import by.drinevskiy.converter.utils.launchUntilPaused
import by.drinevskiy.converter.viewmodel.CommonViewModel
import by.drinevskiy.converter.viewmodel.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//import by.drinevskiy.converter.ui.databinding.FragmentLengthBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LengthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LengthFragment : Fragment() {

    private var _binding: FragmentLengthBinding? = null
    private val binding get() = _binding!!
    private val vm: CommonViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm.clearAll()
        _binding = FragmentLengthBinding.inflate(inflater, container, false)
        lifecycleScope.launchUntilPaused(this){
            vm.state.collect { state ->
                binding.editText1.setText(state.number1)
                binding.editText2.setText(state.number2)
            }
        }
        binding.editText1.setOnFocusChangeListener { view, hasFocus ->
            vm.changeFocus1(hasFocus)
        }
        binding.editText2.setOnFocusChangeListener { view, hasFocus ->
            vm.changeFocus2(hasFocus)
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
        val view = binding.root
        return view
//        return inflater.inflate(R.layout.fragment_length, container, false)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}