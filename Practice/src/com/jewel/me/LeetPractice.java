package com.jewel.me;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class LeetPractice {

	public static void main(String[] args) {

		String s = "bba", t = "ab";

//		System.out.println(t.substring(1, 3));
		System.out.println(minWindow1(s, t));
	}

	public static String minWindow2(String s, String t) {

		Map<Character, Integer> tmap = new HashMap<Character, Integer>();
		Map<Character, Integer> smap = new HashMap<Character, Integer>();

		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			tmap.put(c, tmap.getOrDefault(c, 1));
			smap.put(c, 0);
		}
		
		
		return "";
	}

	public static String minWindow1(String s, String t) {
//		76. Minimum Window Substring
		Map<Character, Integer> mt = new HashMap<Character, Integer>();
		Map<Character, Integer> ms = new HashMap<Character, Integer>();
		int sum = 0, left = 0, right = 0, min = 0, max = 0, len = Integer.MAX_VALUE, found = 0, have = 0;

		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			mt.put(c, mt.getOrDefault(c, 1) + 1);
			ms.put(c, 0);
		}

		while (right < s.length()) {

			char c = s.charAt(right);
			right++;
			if (mt.containsKey(c)) {
				if (ms.get(c) == 0)
					have += 1;
				ms.put(c, ms.get(c) + 1);
				found += 1;

				if (found == have) {
					while (!mt.containsKey(s.charAt(left))) {
						left++;
					}

					if (len > right - left) {
						min = left;
						max = right;
						len = max - min;
					}

					if (ms.containsKey(s.charAt(left))) {
						char ch = s.charAt(left);
						ms.put(ch, ms.get(ch) - 1);
						found -= 1;
					}
					left++;
				}
			}
		}
		return s.substring(min, max);
	}

	public static String minWindow(String s, String t) {

		if (t.length() > s.length())
			return "";

		Map<Character, Integer> arrt = new HashMap<Character, Integer>();
		Map<Character, Integer> arrs = new HashMap<Character, Integer>();
		Map<Integer, String> substr = new HashMap<Integer, String>();

		return minLength(substr);
	}

	public static String minLength(Map<Integer, String> map) {

		int min = Integer.MAX_VALUE;
		for (Map.Entry<Integer, String> set : map.entrySet()) {
			if (set.getKey() < min)
				min = set.getKey();
		}

		return map.get(min);
	}

	public static boolean checkInclusion(String s1, String s2) {

		if (s1.length() > s2.length())
			return false;
		if (s2.contains(s1))
			return true;

		int left = 0, right = s1.length(), match = 26;
		Map<Character, Integer> sc1 = new HashMap<Character, Integer>();
		Map<Character, Integer> sc2 = new HashMap<Character, Integer>();

		for (char c = 'a'; c <= 'z'; c++) {
			sc1.put(c, 0);
			sc2.put(c, 0);
		}

		for (int i = 0; i < s1.length(); i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			sc1.put(c1, sc1.get(c1) + 1);
			sc2.put(c2, sc2.get(c2) + 1);
		}

		for (char c = 'a'; c <= 'z'; c++)
			if (!sc1.get(c).equals(sc2.get(c)))
				match -= 1;

		while (right < s2.length()) {

			if (match == 26)
				return true;

			char cr = s2.charAt(right++);
			sc2.put(cr, sc2.get(cr) + 1);
			if (sc1.get(cr).equals(sc2.get(cr)))
				match += 1;
			else if (sc1.get(cr) + 1 == (sc2.get(cr)))
				match -= 1;

			char cl = s2.charAt(left++);
			sc2.put(cl, sc2.get(cl) - 1);
			if (sc1.get(cl).equals(sc2.get(cl)))
				match += 1;
			else if (sc1.get(cl) + 1 == (sc2.get(cl)))
				match -= 1;

		}

		return match == 26;

	}

	public static int minSubArrayLen(int target, int[] nums) {

//		Arrays.sort(nums);

		int left = 0, right = 0, min = Integer.MAX_VALUE, sum = 0;

		while (right < nums.length) {

			sum += nums[right++];

			while (sum >= target) {
				min = Math.min(right - left, min);
				sum -= nums[left];
				left++;
			}

		}

		return min == Integer.MAX_VALUE ? 0 : min;
	}

	public static int lengthOfLongestSubstring(String s) {

		int max = 0, left = 0, right = 0;

		Map<Character, Integer> map = new HashMap<Character, Integer>();

		while (right < s.length()) {
			char c = s.charAt(right);

			if (map.containsKey(c) && map.get(c) >= left) {
				max = Math.max(max, right - left);
				left = map.get(c) + 1;
			}
			map.put(c, right);
			right++;
		}

		return Math.max(max, right - left);
	}

	public static int[] twoSum(int[] nums, int target) {

		int left = 0, right = nums.length - 1;

		while (left < right) {

			int sum = nums[left] + nums[right];

			if (sum < target)
				left++;
			else if (sum > target)
				right--;
			else
				return new int[] { left, right };
		}

		return null;
	}

	public static double findMaxAverage(int[] nums, int k) {

		int left = 0, right = 0, sum = 0, max;

		while (right < k) {
			sum += nums[right++];
		}
		max = Math.max(Integer.MIN_VALUE, sum);

		while (right < nums.length) {
			sum += nums[right++] - nums[left++];
			max = Math.max(max, sum);
		}
		return max * 1.0 / k;
	}

	public static int maxArea(int[] height) {

		int left = 0, right = height.length - 1, max = 0;

		while (left < right) {
			max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
			int h = Math.min(height[left], height[right]);

			while (left < right && height[left] <= h)
				left++;
			while (left < right && height[right] <= h)
				right--;
		}

		return max;
	}

	public static List<List<Integer>> threeSum(int[] nums) {

		int l = nums.length;
		if (nums == null || l < 3)
			return new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		int left = 0, right = 0;

		for (int i = 0; i < l - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			left = i + 1;
			right = l - 1;
			while (left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if (sum == 0) {
					list.add(Arrays.asList(nums[i], nums[left], nums[right]));
					left++;
					right--;
					while (left < right && nums[left] == nums[left - 1])
						left++;
					while (left < right && nums[right] == nums[right + 1])
						right--;
				} else if (sum > 0) {
					right--;
					while (left < right && nums[right] == nums[right + 1])
						right--;
				} else {
					left++;
					while (left < right && nums[left] == nums[left - 1])
						left++;
				}
			}
		}
		return list;
	}

	public static int subarraySum(int[] nums, int k) {

		int res = 0, sum = 0;

		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		m.put(0, 1);
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			if (m.containsKey(sum - k))
				res += m.get(sum - k);

			m.put(sum, m.getOrDefault(sum, 0) + 1);
		}

		return res;
	}

	public static int findMaxLength(int[] nums) {

		int ans = 0, sum = 0, l = nums.length;
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		m.put(0, -1);
		for (int i = 0; i < l; i++) {
			sum += nums[i] == 1 ? 1 : -1;
			if (m.containsKey(sum))
				ans = Math.max(ans, i - m.get(sum));
			else
				m.put(sum, i);
		}

		m.clear();

		return ans;
	}

	public static int minFlipsMonoIncr(String s) {

		char[] ch = s.toCharArray();
		int l = s.length(), left = 0, right = 0;

		for (int i = 0; i < l / 2; i++)
			if (ch[i] == '1')
				left++;

		for (int i = l % 2 == 0 ? l / 2 : (l / 2) + 1; i < l; i++)
			if (ch[i] == '0')
				right++;

		return left + right;
	}

	public static int monotoneIncreasingDigits(int n) {
		char[] ch = String.valueOf(n).toCharArray();
		int m = ch.length, l = m;

		for (int i = 1; i < l; i++) {
			if (ch[i] < ch[i - 1]) {
				m = i;
				ch[i - 1]--;
				break;
			}
		}

		for (int i = m; i < l; i++) {
			ch[i] = '9';
		}

		if (m == l)
			return n;
		else
			return monotoneIncreasingDigits(Integer.valueOf(new String(ch)));
	}

	public static boolean isMonotonic(int[] nums) {

		boolean isD = false, isI = isD;

		for (int i = 1; i < nums.length; i++) {

			if (nums[i - 1] < nums[i])
				isI = true;
			else if (nums[i - 1] > nums[i])
				isD = true;

			if (isI && isD)
				return false;

		}
		return true;
	}

	public static int nthUglyNumber(int n) {

		int[] arr = new int[n];
		arr[0] = 1;
		int i2 = 0, i3 = 0, i5 = 0;
		int f2, f3, f5;

		for (int i = 1; i < n; i++) {

			arr[i] = Math.min(2 * arr[i - 1], Math.min(3 * arr[i - 1], 5 * arr[i - 1]));

		}

		return arr[n - 1];
	}

	public static int maxProfitcool(int[] prices) {
//		Best Time to Buy and Sell Stock with Cooldown

		int profit = 0;
		int buy = -1;

		for (int i = 0; i < prices.length - 1; i++) {

			if (i == prices.length - 1)
				return buy >= 0 ? profit += prices[i] - prices[buy] : profit;

//			if (prices[i] > prices[i - 1])
//				profit += prices[i] - prices[i-1];

			if (prices[i] >= prices[i + 1] && buy < 0)
				continue;
			else if (prices[i] < prices[i + 1] && buy >= 0)
				continue;
			else if (prices[i] < prices[i + 1] && buy < 0) {
				buy = i;
				continue;
			}
			profit += prices[i] - prices[buy];
			i += 1;
			buy = -1;
		}

		return profit;
	}

	public static int maxProfit2(int[] prices, int i) {
//	122. Best Time to Buy and Sell Stock II
//		int profit = 0;
//		int buy = -1;

		if (prices.length == 1)
			return 0;
		if (i == prices.length - 1)
			return prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0;
//		for (int i = 1; i < prices.length; i++) {

//			if (i == prices.length - 1)
//				return buy >= 0 ? profit += prices[i] - prices[buy] : profit;

//			if (prices[i] > prices[i - 1])
//				profit += prices[i] - prices[i-1];

//			if (prices[i] >= prices[i + 1] && buy < 0)
//				continue;
//			else if (prices[i] < prices[i + 1] && buy >= 0)
//				continue;
//			else if (prices[i] < prices[i + 1] && buy < 0) {
//				buy = i;
//				continue;
//			}
//
//			profit += prices[i] - prices[buy];
//			buy = -1;

//		}

		int p = prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0;
		return maxProfit2(prices, i + 1) + p;
	}

	public static int maxProfit1(int[] prices) {
//		121. Best Time to Buy and Sell Stock

		int min = prices[0];

		int profit = 0;
		for (int i = 1; i < prices.length; i++) {

			int cost = prices[i] - min;
//			profit = Math.max(profit, cost);
			profit = profit > cost ? profit : cost;
//			min = Math.min(min, prices[i]);
			min = min < prices[i] ? min : prices[i];
		}
		return profit;
	}

	public static int trap(int[] height) {

		int left = 0;
		int right = height.length - 1;
		int lefth = height[left];
		int righth = height[right];

		int ans = 0;

		while (left <= right) {
			if (lefth < righth) {

				if (height[left] < lefth)
					ans += lefth - height[left];
				else
					lefth = height[left];
				left++;
			} else {
				if (height[right] < righth)
					ans += righth - height[right];
				else
					righth = height[right];
				right--;
			}
		}

		return ans;

	}

	public static int[] maxSlidingWindow(int[] nums, int k) {

		Deque<Integer> q = new LinkedList<Integer>();
		List<Integer> ans = new ArrayList<Integer>();

		int s = 0, e = s;

		while (e < nums.length) {

			while (!q.isEmpty() && nums[q.peek()] < nums[e])
				q.poll();
			q.add(nums[e]);

			if (s > q.peek())

				s++;
			e++;
		}

		return null;
	}

	public static int[] maxSlidingWindow1(int[] nums, int k) {

//		bruteforce
		List<Integer> ret = new ArrayList<Integer>();

		int s = 0, e = s + k;
		while (e <= nums.length) {
			ret.add(getMax(nums, s, e));
			s++;
			e++;
		}

		int[] ans = new int[ret.size()];
		for (int i = 0; i < ret.size(); i++)
			ans[i] = ret.get(i);
		return ans;
	}

	public static int getMax(int[] nums, int s, int e) {
		int t = Integer.MIN_VALUE;
		while (s < e) {
			t = nums[s] > t ? nums[s] : t;
			s++;
		}
		return t;
	}

	public static int[] rangeAddition(int l, int[][] arr) {

		int[] a = new int[l];
		for (int i = 0; i < arr.length; i++) {

			int strt = arr[i][0];
			int end = arr[i][1];
			int inc = arr[i][2];

			while (strt <= end) {
				a[strt] += inc;
				strt++;
			}
		}

		return a;
	}

	public static int[][] transpose(int[][] matrix) {

		int[][] arr = new int[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				arr[i][j] = matrix[j][i];

		return arr;
	}

	public static int numPairsDivisibleBy60(int[] time) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int t : time) {
			int rem = t % 60;
			int req = 60 - (t % 60);

			if (!map.containsKey(req))
				map.put(rem, 1);
			else
				count++;

		}

		return count;
	}

//	public static int numPairsDivisibleBy60(int[] time) {
//		int array[] = new int[60];
//		int retVal = 0;
//		for (int i : time) {
//			int n = i % 60;
//			retVal += array[n == 0 ? 0 : 60 - n];
//			array[n]++;
//		}
//		return retVal;
//	}

	public static String largestNumber(int[] a) {

		String[] b = Arrays.toString(a).replaceAll("\\[|\\]| ", "").split(",");
		Arrays.sort(b, (c, d) -> (d + c).compareTo(c + d));

		if (b[0].equals("0"))
			return "0";
		String ans = "";
		for (String s : b)
			ans += s;

		return ans;
	}

	public static String largestNumber1(int[] a) {

		String[] b = new String[a.length];
		for (int i = 0; i < a.length; i++)
			b[i] = a[i] + "";

		Arrays.sort(b, (c, d) -> (d + c).compareTo(c + d));

		if (b[0].equals("0"))
			return "0";
		String ans = "";
		for (String s : b)
			ans += s;

		return ans;
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

		int l1 = nums1.length;
		int l2 = nums2.length;

		int[] merged = new int[l1 + l2];

		System.arraycopy(nums1, 0, merged, 0, l1);
		System.arraycopy(nums2, 0, merged, l1, l2);

		Arrays.sort(merged);
		int mid = merged.length;
		System.out.println(mid);
		if (mid / 2 == 0) {
			return (double) merged[0];
		} else if (mid % 2 == 1) {
			return (double) merged[mid / 2];
		} else {
			return (double) (merged[mid / 2] + merged[mid / 2 - 1]) / 2;
		}

	}

	public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {

		int[] merged = IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2)).sorted().toArray();

		int mid = merged.length;
		if (mid / 2 == 0) {
			return (double) merged[0];
		} else if (mid % 2 == 1) {
			return (double) merged[mid / 2];
		} else {
			return (double) (merged[mid / 2] + merged[mid / 2 - 1]) / 2;
		}

	}

	public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {

		int len = nums1.length + nums2.length;
		int[] merged = new int[len];

		for (int i = 0; i < len; i++) {
			if (i < nums1.length) {
				merged[i] = nums1[i];
				continue;
			}
			merged[i] = nums2[i - nums1.length];
		}
		Arrays.sort(merged);
		int mid = len / 2;
		if (mid == 0) {
			return (double) merged[0];
		} else if (len % 2 == 1) {
			return (double) merged[mid];
		} else {
			return (merged[mid] + merged[mid - 1]) / 2.0;
		}
	}

	void merge(int arr[], int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		// Create temp arrays
		int L[] = new int[n1];
		int R[] = new int[n2];

		// Copy data to temp arrays
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		// Merge the temp arrays
		int i = 0, j = 0;
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		// Copy remaining elements of L[] if any
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		// Copy remaining elements of R[] if any
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	// Main function that sorts arr[l..r] using merge()
	void sort(int arr[], int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = (l + r) / 2;

			// Sort first and second halves
			sort(arr, l, m);
			sort(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}
	}

	public static char repeatedCharacter(String s) {
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1))
				return s.charAt(i);
		}
		return ' ';
	}
}
