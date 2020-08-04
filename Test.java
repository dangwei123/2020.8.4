你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]

给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？

 

示例 1:

输入: 2, [[1,0]] 
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/course-schedule
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

方法一：

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> arr=new ArrayList<>();
        int[] edges=new int[numCourses];
        for(int i=0;i<numCourses;i++){
            arr.add(new ArrayList<Integer>());
        } 

        for(int i=0;i<prerequisites.length;i++){
            int[] tmp=prerequisites[i];
            arr.get(tmp[1]).add(tmp[0]);
            edges[tmp[0]]++;
        }

        Queue<Integer> queue=new LinkedList<>();
        int target=0;
        for(int i=0;i<numCourses;i++){
            if(edges[i]==0){
                queue.offer(i);
                target++;
            }
        }

        while(!queue.isEmpty()){
            int num=queue.poll();
            for(int i:arr.get(num)){
                edges[i]--;
                if(edges[i]==0){
                    target++;
                    queue.offer(i);
                }
            }
        }
        return target==numCourses;
    }
}


方法二：

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> arr=new ArrayList<>();
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<numCourses;i++){
            arr.add(new ArrayList<Integer>());
        } 

        for(int i=0;i<prerequisites.length;i++){
                arr.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        Queue<Integer> queue=new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(arr.get(i).size()==0){
                queue.offer(i);
                set.add(i);
            }
        }

        while(!queue.isEmpty()){
            int num=queue.poll();
            for(int i=0;i<numCourses;i++){
                if(!set.contains(i)){
                    int j=0;
                    for(;j<arr.get(i).size();j++){
                        if(!set.contains(arr.get(i).get(j))){
                            break;
                        }
                    }
                    if(j==arr.get(i).size()){
                        queue.offer(i);
                        set.add(i);
                    }
                }
            }
        }
        return set.size()==numCourses;
    }
}
