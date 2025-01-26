package by.drinevskiy.converter.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import by.drinevskiy.converter.ui.databinding.HomePageBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: HomePageBinding
    private var selectedText: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Disable keyboard
        window.setFlags(
            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        binding = HomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(binding.frameLayout.id, LengthFragment()).commit()
            supportFragmentManager.beginTransaction().add(binding.keyboardLayout.id, KeyboardFragment()).commit()
            selectText("Length")
        }
        binding.textViewLength.setOnClickListener {
            replaceFragment(LengthFragment())
            selectText("Length")
        }

        binding.textViewSquare.setOnClickListener {
            replaceFragment(SquareFragment())
            selectText("Square")
        }

        binding.textViewVolume.setOnClickListener {
            replaceFragment(VolumeFragment())
            selectText("Volume")
        }

        updateTextColors()
    }

    private fun selectText(text: String) {
        selectedText = text
        updateTextColors()
    }

    private fun updateTextColors() {
        binding.textViewLength.setTextColor(if (selectedText == "Length") resources.getColor(R.color.selectedColor, theme) else resources.getColor(R.color.textColor, theme))
        binding.textViewSquare.setTextColor(if (selectedText == "Square") resources.getColor(R.color.selectedColor, theme) else resources.getColor(R.color.textColor, theme))
        binding.textViewVolume.setTextColor(if (selectedText == "Volume") resources.getColor(R.color.selectedColor, theme) else resources.getColor(R.color.textColor, theme))
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(binding.frameLayout.id, fragment).commit()
    }
}



@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
