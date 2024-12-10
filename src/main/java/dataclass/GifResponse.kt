package dataclass

data class GiphyResponse(
    val data: List<GifData>
)

data class GifData(
    val images: Images,
    val title: String
)

data class Images(
    val original: OriginalImage
)

data class OriginalImage(
    val url: String
)

