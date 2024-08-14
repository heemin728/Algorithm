#include<iostream>
#define MAX 100002
using namespace std;

int main(){
    int n,m;
    cin >> n >> m;

    int arr[MAX]={0,};
    int sum[MAX]={0,};

    for(int i=1;i<=n;i++){
        cin >> arr[i];
    }

    for(int i=0;i<m;i++){
        int a,b,k;
        cin >> a >> b >> k;
        sum[a]+=k;
        sum[b+1]-=k;
    }

    for(int i=2;i<=n;i++){
        sum[i]+=sum[i-1];
    }
    for(int i=1;i<=n;i++){
        arr[i]+=sum[i];
        cout << arr[i] << " ";
    }
}