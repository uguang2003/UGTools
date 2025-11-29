package top.ug666.ug.tools.bean.textdiff;

import java.util.List;

/**
 * 差异结果类
 * @author CassianFlorin
 * @email flowercard591@gmail.com
 * @date 2025/9/27 18:57
 */
public record DiffResult(List<DiffInfo> leftDiffs, List<DiffInfo> rightDiffs) {}
