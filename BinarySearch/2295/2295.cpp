#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

int N;
vector<int> v;
vector<int> s;

bool binary_search(int target){
    int start=0;
    int end=s.size()-1;
    int mid=0;

    while(start <= end){
        mid=(start+end)/2;

        if(s[mid] < target){
            start=mid+1;
        }
        else if(s[mid]>target){
            end=mid-1;
        }
        else{
            return true;
        }
    }

    return false;
}

int main(){
    cin >> N;
    for(int i=0;i<N;i++){
        int u;
        cin >> u;
        v.push_back(u);
    }
    sort(v.begin(),v.end());

    for(int i=0;i<N;i++){
        for(int j=i;j<N;j++){
            s.push_back(v[i]+v[j]);
        }
    }
    sort(s.begin(),s.end());

    bool flag=true;

    for(int i=N-1;i>=0;i--){
        for(int j=i;j>=0;j--){
            int num=v[i]-v[j];

            if(binary_search(num)){
                cout << v[i] << "\n";
                flag=false;
                break;
            }
        }

        if(!flag){
            break;
        }
    }

}