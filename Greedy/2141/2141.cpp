#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

vector<pair<long long ,long long>> v;
int n;

int main(){
    cin >> n;

    long long total=0;

    for(int i=0;i<n;i++){
        int n1,n2;
        cin >> n1 >> n2;
        v.push_back({n1,n2});
        total+=n2;
    }    

    sort(v.begin(),v.end());

    long long cnt=0;
    for(int i=0;i<n;i++){
        long long town=v[i].first;
        long long people=v[i].second;

        cnt+=people;
        if(cnt >= (total+1)/2){
            cout << town << "\n";
            break;
        }
    }

}