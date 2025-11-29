package top.ug666.ug.tools.bean.textdiff;

import top.ug666.ug.tools.enums.UnifiedSpanTypeEnum;

/**
 * @author CassianFlorin
 * @email flowercard591@gmail.com
 * @date 2025/9/27 18:54
 */
public record Span(int start, int end, UnifiedSpanTypeEnum type) {
}
