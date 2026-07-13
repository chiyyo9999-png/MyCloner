package com.example.mycloner

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyDeviceAdminReceiver : DeviceAdminReceiver() {
    // โค้ดจะทำงานเมื่อระบบ Android อนุญาตให้แอปนี้สร้างพื้นที่แยกสำเร็จ
    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Toast.makeText(context, "สร้างพื้นที่จำลองสำเร็จแล้ว!", Toast.LENGTH_SHORT).show()
    }
}
