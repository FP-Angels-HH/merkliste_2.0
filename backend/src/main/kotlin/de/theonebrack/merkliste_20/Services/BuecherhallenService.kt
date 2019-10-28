package de.theonebrack.merkliste_20.Services

import de.theonebrack.merkliste_20.Models.Media
import de.theonebrack.merkliste_20.WebClient
import org.springframework.stereotype.Component

@Component
class BuecherhallenService(val webClient: WebClient) {
    fun fetchAll(username: String, password: String): List<Media> {
        webClient.login(username, password)

        val result: List<Media> = webClient.getAllMedias()

        for (entry in result) {
            val numberAvailable = webClient.getMediaDetails(entry.url)
            entry.availability  = numberAvailable
        }

        webClient.close()

        return result
    }
}