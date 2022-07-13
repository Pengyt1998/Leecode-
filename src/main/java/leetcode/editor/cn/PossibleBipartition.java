package main.java.leetcode.editor.cn;

//给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。 
//
// 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和 bi的人归入同一组。当可以用
//这种方法将所有人分进两组时，返回 true；否则返回 false。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
//输出：true
//解释：group1 [1,4], group2 [2,3]
// 
//
// 示例 2： 
//
// 
//输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2000 
// 0 <= dislikes.length <= 10⁴ 
// dislikes[i].length == 2 
// 1 <= dislikes[i][j] <= n 
// ai < bi 
// dislikes 中每一组都 不同 
// 
//
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 👍 183 👎 0

import java.util.*;

public class PossibleBipartition{
    public static void main(String[] args) {
        Solution solution = new PossibleBipartition().new Solution();
        
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[] p;

    int find(int x) {
        return p[x] == x ? x : (p[x] = find(p[x]));
    }

    void union(int a, int b) {
        p[find(a)] = find(b);
    }

    boolean query(int a, int b) {
        return find(a) == find(b);
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        p = new int[n + 1];
        List<Integer>[] list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = i;
            list[i] = new ArrayList<>();
        }
        for (int[] e : dislikes) {
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }
        for (int i = 1; i <= n; i++)
            if (list[i].size() > 0) {
                int root = list[i].get(0);
                for (int j : list[i]) {
                    if (query(i, j)) return false;
                    union(root, j);
                }
            }
        return true;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
    }
