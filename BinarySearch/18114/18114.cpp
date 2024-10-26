#include<iostream>
#include<vector>
#include<algorithm>
#define MAX 5001
using namespace std;

int N,C;
vector<int> list;

bool find(int target){

    int left=0;
    int right=N-1;

    int mid=0;

    while(left<right){
        mid=(left+right)/2;

        if(list[mid] < target){
            left=mid+1;
        }
        else if(list[mid] > target){
            right=mid-1;
        }
        else{
            return true;
        }
    }

    return false;
}

int main(){
    cin >> N >> C;

    for(int i=0;i<N;i++){
        int a;
        cin >> a;

        if(a==C){
            cout << 1 << "\n";
            return 0;
        }

        list.push_back(a);
    }

    sort(list.begin(),list.end());

    int left=0;
    int right=N-1;

    int num=0;

    while(left<right){
        num = list[left]+list[right];
        if(num == C){
            cout << 1 << "\n";
            return 0;
        }

        //bool result = find(C-num);
        if(C-num != list[left] && C-num != list[right] && find(C-num)){
            cout << 1 << "\n";
            return 0;
        }

        if(num < C){
            left++;
        }    
        else if(num > C){
            right--;
        }

    }

    cout << 0 << "\n";
}