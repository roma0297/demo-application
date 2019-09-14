package ru.raiffeisen.demoapplication.services

import com.fasterxml.uuid.Generators
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException

@Component
class MediaStorageService() {

    fun saveFiles(files: List<MultipartFile>) {
        files.forEach { file ->
            try {
                val nameUuid = Generators.timeBasedGenerator().generate().toString()
                val extension = file.originalFilename?.substringAfterLast('.') ?: ".txt"
                val tempFileName = "$nameUuid.$extension"

                file.transferTo(File("${System.getProperty("user.home")}/media/$tempFileName"))
            } catch (e: IOException) {
                logger.error("Exception occurred during file saving", e)
            }
        }
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(MediaStorageService::class.java)
    }

}