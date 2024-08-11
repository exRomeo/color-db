package com.example.roomdbdemo

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbdemo.data.localsource.room_db.database.ColorDataBase

const val TAG = "slider"
class MainActivity : AppCompatActivity() {

    val dao by lazy { ColorDataBase.getInstance(this.applicationContext).dao }
    val viewModel by viewModels<MainActivityViewModel>(factoryProducer = {
        MainActivityViewModelFactory(dao)
    })
    lateinit var colorRecyclerView: RecyclerView
    lateinit var rSlider: SeekBar
    lateinit var gSlider: SeekBar
    lateinit var bSlider: SeekBar
    lateinit var colorBox: LinearLayout
    lateinit var saveButton: Button

    var r = 0
    var g = 0
    var b = 0
    @OptIn(ExperimentalStdlibApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViews()

        saveButton.setOnClickListener {
            viewModel.insertColor(ColorModel(id = r+g+b, name = Color.rgb(r,g,b).toHexString(HexFormat.UpperCase), r = r, g = g, b = b))
        }
        rSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                r = p0?.progress ?: 0
                colorBox.setBackgroundColor(Color.rgb(r,g,b))
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        gSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
               g= p0?.progress ?: 0
                colorBox.setBackgroundColor(Color.rgb(r,g,b))

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        bSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                b = p0?.progress ?: 0
                colorBox.setBackgroundColor(Color.rgb(r,g,b))

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
    }


    private fun setupViews() {
        saveButton = findViewById(R.id.button)
        rSlider = findViewById(R.id.r_seekbar)
        gSlider = findViewById(R.id.g_seekbar)
        bSlider = findViewById(R.id.b_seekbar)
        colorBox = findViewById(R.id.color_box)
        colorRecyclerView = findViewById(R.id.color_rcv)
        val adapter = RCVAdapter(emptyList())

        viewModel.colorsList.observe(this) {
            adapter.updateData(it)
        }
        colorRecyclerView.layoutManager = LinearLayoutManager(this)

        colorRecyclerView.adapter = adapter
    }
}