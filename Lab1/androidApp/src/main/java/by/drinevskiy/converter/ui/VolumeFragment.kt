package by.drinevskiy.converter.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import by.drinevskiy.converter.android.BuildConfig
import by.drinevskiy.converter.android.databinding.FragmentVolumeBinding
import by.drinevskiy.converter.utils.copyToClipboard
import by.drinevskiy.converter.utils.launchUntilPaused
import by.drinevskiy.converter.viewmodel.CommonViewModel

class VolumeFragment : Fragment() {

    private var _binding: FragmentVolumeBinding? = null
    private val binding get() = _binding!!
    private val vm: CommonViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVolumeBinding.inflate(inflater, container, false)

        val adapter1 = ArrayAdapter.createFromResource(requireContext(), by.drinevskiy.converter.android.R.array.volume_array, R.layout.simple_spinner_item)
        adapter1.setDropDownViewResource(R.layout.simple_dropdown_item_1line)
        binding.spinner1.adapter = adapter1

        val adapter2 = ArrayAdapter.createFromResource(requireContext(), by.drinevskiy.converter.android.R.array.volume_array, R.layout.simple_spinner_item)
        adapter2.setDropDownViewResource(R.layout.simple_dropdown_item_1line)
        binding.spinner2.adapter = adapter2

        lifecycleScope.launchUntilPaused(this){
            vm.state.collect { state ->
                binding.editText1.setText(state.number1)
                binding.editText2.setText(state.number2)
                binding.spinner1.setSelection(adapter1.getPosition(vm.state.value.unit1))
                binding.spinner2.setSelection(adapter2.getPosition(vm.state.value.unit2))
            }
        }
        binding.editText1.setOnFocusChangeListener { view, hasFocus ->
            vm.changeFocus1(hasFocus)
        }
        binding.editText2.setOnFocusChangeListener { view, hasFocus ->
            vm.changeFocus2(hasFocus)
        }

        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                vm.selectUnit1(parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, если ничего не выбрано (можно оставить пустым)
            }
        }
        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                vm.selectUnit2(parent.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Действия, если ничего не выбрано (можно оставить пустым)
            }
        }
        if(BuildConfig.IS_PREMIUM) {
            binding.root.findViewById<ImageButton>(by.drinevskiy.converter.android.R.id.imageButton)
                ?.setOnClickListener {
                    vm.exchangeValues()
                }
            binding.root.findViewById<ImageButton>(by.drinevskiy.converter.android.R.id.imageButton3)
                ?.setOnClickListener {
                    copyToClipboard(requireContext(), binding.editText1.text.toString())
                }
            binding.root.findViewById<ImageButton>(by.drinevskiy.converter.android.R.id.imageButton4)
                ?.setOnClickListener {
                    copyToClipboard(requireContext(), binding.editText2.text.toString())
                }
        }
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vm.clearAll()
        _binding = null
    }
}