package com.jewel.me;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

public class LeetPractice {

	public static void main(String[] args) {

		int[] time = { 115, 60, 60, 60, 5 };
		int[] time1 = { 30, 20, 150, 100, 40 };

		int[] prices = { 1, -1, 0 };
//		1,2,3,4,5,6 7, 1, 5, 3, 6, 4 

//		System.out.println('1' - 0);
		String s = "00011000";

		System.out.println(subarraySum(prices, 0));

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
