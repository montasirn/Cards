import java.util.HashMap;
import java.util.Map;

class Checker {
    public static boolean twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return true;
            }
        }
        // In case there is no solution, return an empty array
        return false;
    }
}