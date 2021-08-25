package com.example.alphauser.UI.Ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alphauser.R
import com.example.alphauser.UI.Camera.CameraClass
import com.example.alphauser.UI.Map.MapClass
import com.example.userdataapp.Adapters.UserAdapters
import com.example.userdataapp.ApiService.UserApi
import com.example.userdataapp.ApiService.UserApiInstance
import com.example.userdataapp.Model.Data
import com.example.userdataapp.Model.UserData
import com.example.userdataapp.Repository.UserRepository
import com.example.userdataapp.ViewModel.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), UserAdapters.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: View
    private lateinit var fname: EditText
    private lateinit var lname: EditText
    private lateinit var emailId: EditText
    private lateinit var id: EditText
    private lateinit var btnSave: Button
    private lateinit var userAdapters: UserAdapters
    private lateinit var viewModel: UserViewModel
    private lateinit var dialog: BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_user)
        fab = findViewById(R.id.fab)
        getUserDataFromRetrofit(this)
        userAdapters = UserAdapters(this, ArrayList<Data>(),this)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter = userAdapters
        }
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.getUserDataFromDB(applicationContext)?.observe(this, Observer {
            userAdapters.setUserDataAdapter(it as ArrayList<Data>)
        })
        fab.setOnClickListener{
            addUserDialog()
            dialog.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.photo -> {
            intent = Intent(this,CameraClass::class.java)
            startActivity(intent)
            true
        }
        R.id.video -> {
            intent = Intent(this,CameraClass::class.java)
            intent.putExtra("video",true)
            startActivity(intent)
            true
        }
        R.id.map -> {
            intent = Intent(this,MapClass::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun addUserDialog() {
        dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.add_users,null)
        dialog.setContentView(view)
        dialog.setCancelable(true)
        fname = view.findViewById(R.id.fnamedialog)
        lname = view.findViewById(R.id.lnamedialog)
        emailId = view.findViewById(R.id.user_Email_dialog)
        id = view.findViewById(R.id.id_dialog)
        id.text.clear()
        fname.text.clear()
        lname.text.clear()
        emailId.text.clear()
        btnSave = view.findViewById(R.id.btn_save)
        btnSave.setOnClickListener {
            addNewUserInDb()
            dialog.dismiss()
        }
    }

    private fun addNewUserInDb() {
        val fname = fname.text.toString().trim()
        val lname = lname.text.toString().trim()
        val email = emailId.text.toString().trim()
        val id = id.text.toString().trim()
        if (!TextUtils.isEmpty(fname) && !TextUtils.isEmpty(fname) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(id)){
            viewModel.insert(this, listOf(Data("",email,fname,Integer.parseInt(id),lname)))
        }else{
            Toast.makeText(applicationContext,"Please fill data", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onItemClick(userdata: Data) {
        intent = Intent(this,ProfileDetails::class.java)
        intent.putExtra("ProfileUserId", userdata.id.toString())
        intent.putExtra("ProfileImage",userdata.avatar)
        intent.putExtra("profileName","${userdata.first_name} ${userdata.last_name}")
        intent.putExtra("profileEmail",userdata.email)
        startActivity(intent)
    }

    private fun getUserDataFromRetrofit(context: Context) {
        val userApiInstance = UserApiInstance.getUserApiInstance().create(UserApi::class.java)
        val call = userApiInstance.getUserData()
        call.enqueue(object : retrofit2.Callback<UserData>{
            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                if(response.isSuccessful){
                    Thread(Runnable {
                        viewModel.insert(context, response.body()?.data as List<Data>)
                    }).start()
                    Log.d("data", response.body()?.data.toString())
                }
            }
            override fun onFailure(call: Call<UserData>, t: Throwable) {
                Log.d("msg",t.message.toString())
            }
        })
    }
}