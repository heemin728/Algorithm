#include<iostream>
#include<vector>
#include<algorithm>
#define MAX 2001
using namespace std;

int N;
vector<int> v;
vector<int> pre(MAX);
int answer=0;
int sdiff=9876554321;
int s=0;

int getSum(int start, int end){
    return pre[end+1]-pre[start];
}

int main(){
    cin >> N;

    for(int i=0;i<N;i++){
        int a;
        cin >> a;
        v.push_back(a);
        pre[i+1]=pre[i]+a;
    }

    for(int left=0;left<N-1;left++){

        for(int right=left+1;right<N;right++){

            int wsum=getSum(left,right);
            int start=left;
            int end=right;
            int mid=0;

            while(start <= end){
                mid=(start+end)/2;

                int leftSum=getSum(left,mid);
                int rightSum=wsum-leftSum;

                int diff=abs(leftSum-rightSum);

                if(diff < sdiff){
                    answer=wsum;
                    sdiff=diff;
                }
                else if(diff==sdiff){
                    if(wsum > answer){
                        answer=wsum;
                        sdiff=diff;
                    }
                }

                if(leftSum > rightSum){
                    end=mid-1;
                }
                else{
                    start=mid+1;
                }
            }
            
        }
    }
    cout << answer << "\n";
}
