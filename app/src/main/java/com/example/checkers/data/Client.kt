package com.example.checkers.data

import java.io.OutputStreamWriter
import java.net.Socket

class Client(private val serverIp: String, private val serverPort: Int) {
    fun sendData(data: String) {
        val socket = Socket(serverIp, serverPort)

        val writer = OutputStreamWriter(socket.getOutputStream())
        writer.write(data)
        writer.flush()
        writer.close()

        socket.close()
    }
}