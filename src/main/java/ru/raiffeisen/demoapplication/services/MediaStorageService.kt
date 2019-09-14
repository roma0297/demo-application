package ru.raiffeisen.demoapplication.services

import com.fasterxml.uuid.Generators
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import ru.raiffeisen.demoapplication.common.operation.OperationResult
import java.io.File
import java.io.IOException

@Component
class MediaStorageService {

    fun saveFiles(files: List<MultipartFile>): OperationResult {
        files.forEach { file ->
            try {
                val nameUuid = Generators.timeBasedGenerator().generate().toString()
                val extension = file.originalFilename?.substringAfterLast('.') ?: ".txt"
                val tempFileName = "$nameUuid.$extension"

                val userHome = System.getProperty("user.home")
                file.transferTo(File("$userHome/media/$tempFileName"))
            } catch (e: IOException) {
                val errorMessage = "File saving finished incorrectly"
                logger.error(errorMessage, e)
                return OperationResult.failure("$errorMessage: ${e.message}")
            }
        }
        return OperationResult.success()
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MediaStorageService::class.java)
    }
}
