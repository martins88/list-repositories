package br.com.eduardo.martins.githubrepositories.utils

import androidx.test.platform.app.InstrumentationRegistry
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception


object AssetsLoader {

    /**
     * Load a json stored in the assets folder and returns the content string
     */
    fun loadAsset(assetName: String): String {
        return InstrumentationRegistry
            .getInstrumentation()
            .context
            .assets
            .open(assetName)
            .readContent()
    }

    /**
     * Reads the data for the given input stream
     */
    private fun InputStream.readContent(): String {

        var content = ""
        try {
            val inputStreamReader = InputStreamReader(this)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString = bufferedReader.readLine()

            while (receiveString != null) {
                content += receiveString
                receiveString = bufferedReader.readLine()
            }
            this.close()

        } catch (e: Exception) {
        }

        return content
    }

}