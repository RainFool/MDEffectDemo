package com.rainfool.json.entity

import android.text.TextUtils
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 *
 * @author krystian
 */

object TestJsonStr {
    const val styleConfig = "{\"clickIntent\":\"\",\"darkMode\":\"0\",\"darkModeNormalImage\":\"https://mlol-75948.qpic.cn/common/dded633ef37074d9d629fc91a19afc29/0\",\"darkModeNormalTitleColorString\":\"#8F8F8F\",\"hiddenPublicTitle\":true,\"highLightImage\":\"https://mlol-75948.qpic.cn/common/3591c4b1542ae90dce668d9eb03868fe/0\",\"highLightImageHeight\":32,\"highLightImageWidth\":42,\"highLightTitle\":\"资讯\",\"highLightTitleColorString\":\"#1c1c1c\",\"isDefualtIndex\":true,\"key\":\"news\",\"normalImage\":\"https://mlol-75948.qpic.cn/common/ca2b2fbbf47e35d3d25ccb0fb1dbfa37/0\",\"normalImageHeight\":32,\"normalImageWidth\":42,\"normalTitle\":\"资讯\",\"normalTitleColorString\":\"#A0ACB5\",\"schemeurl\":\"qtpage://news?zone\\u003dplat\",\"tabBar\":{\"backgroundColor\":\"#ffffff\",\"backgroundHighImgUrl\":\"\",\"backgroundImgUrl\":\"\",\"darkMode\":\"0\",\"gameIcon\":\"https://mlol-75948.qpic.cn/common/fff9e2dfef85657d5a54536151c8419d/0\",\"gameId\":\"plat\",\"lastModify\":0,\"right_items\":[{\"dark_image_url\":\"https://mlol-75948.qpic.cn/common/f8e75849d04a7475a1f460e4a7786516/0\",\"image_url\":\"https://mlol-75948.qpic.cn/common/72bbaa7e363ae13918c77e6eb3d59618/0\",\"intent\":\"qtpage://feed_search\",\"is_visible_in_audit\":\"1\",\"mark\":\"search\",\"red_dot_key\":\"\"},{\"dark_image_url\":\"https://mlol-75948.qpic.cn/common/50c94b74f3155bb933b9e260bdbc3e49/0\",\"image_url\":\"https://mlol-75948.qpic.cn/common/794e55c9b7be00bc1191e79e699cff18/0\",\"intent\":\"https://jcc.qq.com/act/a20220726anniversary/index.html?exchangeType\\u003d1\",\"is_visible_in_audit\":\"1\",\"mark\":\"gamecenter\",\"red_dot_key\":\"\"},{\"dark_image_url\":\"https://mlol-75948.qpic.cn/common/a42cd3a2e3ec8f57b2c46f9e764a541f/0\",\"image_url\":\"https://mlol-75948.qpic.cn/common/9c69cddcc88c1cc72a05b94cf6937b00/0\",\"intent\":\"qtpage://friend_main?configKey\\u003dsocial_contact_tab\",\"is_visible_in_audit\":\"1\",\"mark\":\"messagecenter\",\"red_dot_key\":\"MessageTotal\"}],\"searchBarInfo\":[{\"searchPlaceholder\":\"金铲铲之战一周年\",\"searchSchemeUrl\":\"qtpage://feed_search?keyWords\\u003d%E9%87%91%E9%93%B2%E9%93%B2%E4%B9%8B%E6%88%98%E4%B8%80%E5%91%A8%E5%B9%B4\",\"searchSuffixIcon\":\"https://mlol-file.qpic.cn/mobile/mlol/images/icon-search-new_2x.png\"},{\"searchPlaceholder\":\"许愿\",\"searchSchemeUrl\":\"qtpage://feed_search?keyWords\\u003d%E8%AE%B8%E6%84%BF\",\"searchSuffixIcon\":\"\"},{\"searchPlaceholder\":\"战斗之夜\",\"searchSchemeUrl\":\"qtpage://feed_search?keyWords\\u003d%E6%88%98%E6%96%97%E4%B9%8B%E5%A4%9C\",\"searchSuffixIcon\":\"\"},{\"searchPlaceholder\":\"每日一笑\",\"searchSchemeUrl\":\"qtpage://feed_search?keyWords\\u003d%E6%AF%8F%E6%97%A5%E4%B8%80%E7%AC%91\",\"searchSuffixIcon\":\"\"},{\"searchPlaceholder\":\"入盟照\",\"searchSchemeUrl\":\"qtpage://feed_search?keyWords\\u003d%E5%85%A5%E7%9B%9F%E7%85%A7\",\"searchSuffixIcon\":\"\"},{\"searchPlaceholder\":\"解锁\",\"searchSchemeUrl\":\"qtpage://feed_search?keyWords\\u003d%E8%A7%A3%E9%94%81\",\"searchSuffixIcon\":\"\"},{\"searchPlaceholder\":\"掌盟戏精王\",\"searchSchemeUrl\":\"qtpage://feed_search?keyWords\\u003d%E6%8E%8C%E7%9B%9F%E6%88%8F%E7%B2%BE%E7%8E%8B\",\"searchSuffixIcon\":\"\"},{\"searchPlaceholder\":\"金铲铲之战一周年\",\"searchSchemeUrl\":\"qtpage://feed_search?keyWords\\u003d%E9%87%91%E9%93%B2%E9%93%B2%E4%B9%8B%E6%88%98%E4%B8%80%E5%91%A8%E5%B9%B4\",\"searchSuffixIcon\":\"\"},{\"searchPlaceholder\":\"乌迪尔\",\"searchSchemeUrl\":\"qtpage://feed_search?keyWords\\u003d%E4%B9%8C%E8%BF%AA%E5%B0%94\",\"searchSuffixIcon\":\"\"},{\"searchPlaceholder\":\"转区\",\"searchSchemeUrl\":\"qtpage://feed_search?keyWords\\u003d%E8%BD%AC%E5%8C%BA\",\"searchSuffixIcon\":\"\"}],\"searchPlaceholder\":\"金铲铲福星临门\",\"searchSchemeUrl\":\"qtpage://feed_search?keyWords\\u003d%E9%87%91%E9%93%B2%E9%93%B2%E7%A6%8F%E6%98%9F%E4%B8%B4%E9%97%A8\",\"searchSuffixIcon\":\"\",\"statusBar\":\"1\"}}"
}

@Keep
class ZoneConfig {
    var ret_code = 0
    var common: CommonInfo? = null
    var list: List<TabInfo?>? = null

    @Keep
    class CommonInfo : Serializable {
        var statusBar: String? = null
        var backgroundColor: String? = null
        var searchSuffixIcon: String? = null
        var searchSchemeUrl: String? = null
        var searchPlaceholder: String? = null
        var searchBarInfo: MutableList<SearchItemInfo>? = null
        var gameId: String? = null
        var gameIcon: String? = null
        var backgroundImgUrl: String? = null
        var backgroundHighImgUrl: String? = null
        var lastModify: Long = 0
        var darkMode: String? = null
        /**
         * 右侧区域按钮，按照
         */
        @SerializedName("right_items")
        var rightItems: List<RightItem>? = null

        val isDarkMode: Boolean
            get() = TextUtils.equals(darkMode, "1")

        val isLightMode: Boolean
            get() = TextUtils.equals(statusBar, "1")

        fun showTitleView(): Boolean {
            return TextUtils.equals(statusBar, "1") || TextUtils.equals(statusBar, "2")
        }

        companion object {
            const val TITLE_VIEW_LIGHT_MODE = "1"
            const val TITLE_VIEW_DARK_MODE = "2"
        }
    }

    @Keep
    class TabInfo {
        var key: String? = null
        var schemeurl: String? = null
        var normalTitle: String? = null
        var highLightTitle: String? = null
        var normalTitleColorString: String? = null
        var highLightTitleColorString: String? = null
        var normalImage: String? = null
        var highLightImage: String? = null
        var normalImageWidth = 0
        var normalImageHeight = 0
        var highLightImageWidth = 0
        var highLightImageHeight = 0
        var isDefualtIndex = false
        var clickIntent: String? = null
        var darkMode: String? = null
        var darkModeNormalImage: String? = null
        var darkModeNormalTitleColorString: String? = null
        var tabBar: CommonInfo? = null
        var clickIntentAlgo: MutableMap<String, Any?>? = null

        private var hiddenPublicTitle = false

        fun hiddenPublicTitle(): Boolean {
            return hiddenPublicTitle || tabBar == null || !tabBar!!.showTitleView()
        }

        fun isDarkMode(): Boolean {
            return TextUtils.equals("1", darkMode)
        }

        fun setHiddenPublicTitle(hiddenPublicTitle: Boolean) {
            this.hiddenPublicTitle = hiddenPublicTitle
        }
    }

    @Keep
    data class SearchItemInfo(
        var searchSuffixIcon: String?,
        var searchSchemeUrl: String?,
        var searchPlaceholder: String?
    )

    @Keep
    data class RightItem(
        @SerializedName("image_url")
        var imageUrl: String? = null,
        @SerializedName("red_dot_key")
        var redDotKey: String? = null,
        @SerializedName("intent")
        var intent: String? = null,
        @SerializedName("dark_image_url")
        var darkImageUrl: String? = null,
        @SerializedName("mark")
        var mark: String? = null,
        /**
         * 审计渠道是否展示
         */
        @SerializedName("is_visible_in_audit")
        var isVisibleInAudit: String? = "1"
    ) {
        companion object {
            private const val GAME_CENTER_MARK = "gamecenter"
        }

        fun isGameCenterMark() = mark == GAME_CENTER_MARK
    }
}