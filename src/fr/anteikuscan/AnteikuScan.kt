package eu.kanade.tachiyomi.extension.fr.anteikuscan

import eu.kanade.tachiyomi.source.model.SManga
import eu.kanade.tachiyomi.source.online.ParsedHttpSource
import okhttp3.Request
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class AnteikuScan : ParsedHttpSource() {

    override val name = "Anteiku Scan"
    override val baseUrl = "https://anteikuscan.fr"
    override val lang = "fr"
    override val supportsLatest = true

    override fun latestUpdatesRequest(page: Int): Request {
        return GET("$baseUrl/latest?page=$page", headers)
    }

    override fun latestUpdatesSelector() = "div.manga"
    override fun latestUpdatesFromElement(element: Element): SManga {
        val manga = SManga.create()
        manga.title = element.select("a.title").text()
        manga.setUrlWithoutDomain(element.select("a").attr("href"))
        manga.thumbnail_url = element.select("img").attr("src")
        return manga
    }

    override fun latestUpdatesNextPageSelector() = "a.next"

    // Implement other necessary methods
}
