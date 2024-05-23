package com.duyle.lab4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duyle.lab4.ui.theme.Lab4Theme

class MainActivity : ComponentActivity() {

    var startForResult: ActivityResultLauncher<Intent>? = null

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                //  you will get result here in result.data
                val data = result.data?.getStringExtra(KEY_DATA_NHANVIEN)
                Toast.makeText(
                    baseContext,

                    data,
                    Toast.LENGTH_LONG

                ).show()
            }
        }

        setContent {
            Lab4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )

                    LoginScreen(startForResult)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreen(startForResult: ActivityResultLauncher<Intent>? = null){
    val context = LocalContext.current // getApplicationContext ()
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(30.dp))
        Image(painter = painterResource(id =
        R.drawable.ic_launcher_foreground), contentDescription =
        "Logo")
        OutlinedTextField(value = userName, onValueChange = {
            userName = it }, label = { Text(text = "UserName") }) // EditText
        OutlinedTextField(value = password, onValueChange = {
            password = it }, label = { Text(text = "Password") })
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            if (userName.isNotBlank() && password.isNotBlank()) {
                Toast.makeText(context, "Login successful",
                    Toast.LENGTH_LONG).show()

                val intent = Intent(context, Bai2Activity::class.java)
                val nhanvien = NhanVienModel(userName, password)
                intent.putExtra(KEY_DATA_NHANVIEN, nhanvien)

                startForResult?.launch(intent)
                //context.startActivity(intent)
            } else {
                Toast.makeText(
                    context,

                    "Please enter username and password",
                    Toast.LENGTH_LONG

                ).show()
            }
        }, colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White)) {
            Text(text = "Login")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun GreetingPreview() {
    Lab4Theme {
        Greeting("Android")
    }
}