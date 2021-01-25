package jgeun.study.flo_a_jeffrey.src.main.storage.addfolder

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import jgeun.study.flo_a_jeffrey.config.BaseActivity
import jgeun.study.flo_a_jeffrey.databinding.ActivityStorageAddFolderBinding
import jgeun.study.flo_a_jeffrey.src.main.MainActivity

class StorageAddFolderActivity : BaseActivity<ActivityStorageAddFolderBinding>(ActivityStorageAddFolderBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.addFolderEtListName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.addFolderEtListName.length() == 0){
                    binding.addFolderIvListClear.visibility = View.INVISIBLE
                }else{
                    binding.addFolderIvListClear.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}

        })

        binding.addFolderIvListClear.setOnClickListener{
            binding.addFolderEtListName.setText("")
            binding.addFolderIvListClear.visibility = View.INVISIBLE
        }

        binding.addFolderCancel.setOnClickListener{
            finish()
        }
        binding.addFolderOK.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("listName", binding.addFolderEtListName.text.toString())
            setResult(RESULT_OK, intent)
            finish()
            overridePendingTransition(0, 0);
        }
    }
}