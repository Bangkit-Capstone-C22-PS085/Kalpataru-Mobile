package com.akhmadkhasan68.kalpataru.ui.camera

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProvider
import com.akhmadkhasan68.kalpataru.R
import com.akhmadkhasan68.kalpataru.data.remote.response.partials.Trash
import com.akhmadkhasan68.kalpataru.databinding.ActivityInsertBinding
import com.akhmadkhasan68.kalpataru.model.UserPreference
import com.akhmadkhasan68.kalpataru.ui.ViewModelFactory
import com.akhmadkhasan68.kalpataru.ui.history.HistoryFragment
import com.akhmadkhasan68.kalpataru.ui.main.MainActivity
import com.akhmadkhasan68.kalpataru.utils.rotateBitmap
import java.io.File

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class InsertActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsertBinding
    private var getFile: File? = null
    private lateinit var insertViewModel: InsertViewModel
    private lateinit var dataTrash : Trash

    companion object{
        const val CAMERA_X_RESULT = "camera_x_result"
        const val PICTURE_RESULT = "picture_result"
        const val PICTURE_RESULT_BASE64 = "pricture_result_base64"
        const val IS_BACK_CAMERA = "is_back_camera"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupViewModel()
        setImage()
        setupActions()
    }

    private fun setupActions() {
        binding.editTextNumber.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                return
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0?.length != 0){
                    updateNominal(dataTrash.price)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                return
            }
        })

        binding.button.setOnClickListener {
            insertViewModel.createCart(dataTrash.id, binding.editTextNumber.text.toString().toInt(), binding.tvNominalinfo.text.toString())
        }
    }

    private fun setupViewModel() {
        insertViewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[InsertViewModel::class.java]

        insertViewModel.isLoading.observe(this, { isLoading ->
            showLoading(isLoading)
        })

        insertViewModel.isError.observe(this, { isError ->
            if(isError){
                startActivity(Intent(this@InsertActivity, MainActivity::class.java))
            }
        })

        insertViewModel.isSuccess.observe(this, {
            Log.e("sukses ", it.toString())
            if(it){
                startActivity(Intent(this@InsertActivity, MainActivity::class.java))
                finish()
            }
        })

        insertViewModel.message.observe(this, {
            Log.e("message", it)
            Toast.makeText(this@InsertActivity, it, Toast.LENGTH_SHORT).show()
        })

        insertViewModel.data.observe(this, {
            dataTrash = it
            binding.tvTrashnameinsert.text = it.jenis
            binding.tvPrice.text = it.price.toString()
            binding.editTextNumber.setText("1")
            updateNominal(it.price)
        })
    }

    private fun updateNominal(price : Int?){
        val quantity = binding.editTextNumber.text.toString().toInt()

        if (price != null) {
            binding.tvNominalinfo.text = (price * quantity).toString()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if(isLoading){
            binding.loading.visibility = View.VISIBLE
        }else{
            binding.loading.visibility = View.GONE
        }
    }

    private fun setImage() {
        val cameraResult = intent.getIntExtra(CAMERA_X_RESULT, 0)
        if(cameraResult == 200){
            val myFile = intent.getSerializableExtra(PICTURE_RESULT) as File
            val b64 = intent.getStringExtra(PICTURE_RESULT_BASE64)
            val isBackCamera = intent.getBooleanExtra(IS_BACK_CAMERA, true) as Boolean

            getFile = myFile
            val result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                isBackCamera
            )

            binding.imgTrash.setImageBitmap(result)

            if (b64 != null) {
                insertViewModel.getDataPrediction(b64)
            }
        }else{
            Log.e("camera result", cameraResult.toString())
        }
    }
}