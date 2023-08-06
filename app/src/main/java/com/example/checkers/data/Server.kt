package com.example.checkers.data

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket

class Server(private val serverPort: Int) {

    fun startListening() {
        val serverSocket = ServerSocket(serverPort)

        while (true) {
            val clientSocket = serverSocket.accept()

            val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
            val receivedData = reader.readLine()
            println("Received data from client: $receivedData")

            clientSocket.close()
        }
    }
}