#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

int N;
vector<int> v;
long long answer=0;

int find1(int target){
    int start=0;
    int end=N-1;
    int mid=0;

    while(start <= end){
        mid=(start+end)/2;

        if(v[mid] < target){
            start=mid+1;
        }
        else {
            end=mid-1;
        }
    }
    return start;
}

int find2(int target){
    int start=0;
    int end=N-1;
    int mid=0;

    while(start <= end){
        mid=(start+end)/2;

        if(v[mid] <= target){
            start=mid+1;
        }
        else{
            end=mid-1;
        }
    }
    return end;
}

int main(){
    cin >> N;

    for(int i=0;i<N;i++){
        int a;
        cin >> a;
        v.push_back(a);
    }

    sort(v.begin(),v.end());
    
    int total=0;

    for(int i=0;i<N;i++){
        for(int j=i+1;j<N;j++){
            int num1=v[i];
            int num2=v[j];

            int target=-1*(num1+num2);
            int f1=find1(target);
            int f2=find2(target);
            total = (f2-f1)+1;

            if(i>= f1 && i<=f2){
                total--;
            }
            if(j>= f1 && j<=f2){
                total--;
            }
            answer+=total;
        }
    }
    cout << answer/3 << "\n";
}