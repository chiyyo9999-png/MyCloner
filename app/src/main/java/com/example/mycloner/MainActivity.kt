package com.example.mycloner

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dpm: DevicePolicyManager
    private lateinit var adminComponent: ComponentName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // สั่งสร้างปุ่มกดขึ้นมาบนหน้าจอแบบง่ายๆ โดยไม่ต้องใช้ไฟล์ layout
        val btnCreate = Button(this).apply {
            text = "กดตรงนี้เพื่อสร้างแอปโคลน (Work Profile)"
        }
        setContentView(btnCreate)

        dpm = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        adminComponent = ComponentName(this, MyDeviceAdminReceiver::class.java)

        btnCreate.setOnClickListener {
            startCloneSpace()
        }
    }

    private fun startCloneSpace() {
        // ตรวจสอบว่าเครื่องเคยสร้างพื้นที่แยกตัวนี้ไปหรือยัง
        if (!dpm.isProfileOwnerApp(packageName)) {
            val intent = Intent(DevicePolicyManager.ACTION_PROVISION_MANAGED_PROFILE).apply {
                putExtra(DevicePolicyManager.EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME, adminComponent)
            }
            
            // ส่งคำสั่งไปหา Android OS ให้เริ่มจำลองระบบขึ้นมา
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 100)
            } else {
                Toast.makeText(this, "มือถือของคุณไม่รองรับการโคลนระบบนี้", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "คุณเปิดใช้งานพื้นที่โคลนอยู่แล้ว", Toast.LENGTH_SHORT).show()
        }
    }
}
